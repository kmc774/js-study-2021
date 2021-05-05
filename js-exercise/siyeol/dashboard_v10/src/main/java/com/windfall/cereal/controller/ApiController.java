package com.windfall.cereal.controller;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.windfall.cereal.dto.BoardVO;
import com.windfall.cereal.dto.LikeVO;
import com.windfall.cereal.dto.MemberVO;
import com.windfall.cereal.dto.board.BoardResponse;
import com.windfall.cereal.page.PageMaker;
import com.windfall.cereal.service.BoardService;
import com.windfall.cereal.service.CommentService;
import com.windfall.cereal.service.LikeService;
import com.windfall.cereal.service.MemberService;

@RestController
@RequestMapping(value = {"/api"}, method = {RequestMethod.GET, RequestMethod.POST})
public class ApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Inject
	private BoardService boardService;
	@Inject
	private MemberService memberService;
	@Inject
	private LikeService likeService;
	@Inject
	private CommentService commentService;
	
	@GetMapping(value = "/board/loadList.do", produces = "application/json")
	public Map<String, Object> apiList(HttpServletRequest request,
							@RequestParam(defaultValue = "1") String pageNum, 
							@RequestParam(defaultValue = "10") String contentNum,
							@RequestParam(required = false) String searchType,
							@RequestParam(required = false) String searchValue) throws Exception {
		
		logger.info("==api loadList.do==");	
		
		List<BoardResponse> boardList;
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pageNum", pageNum);
		paramMap.put("contentNum", contentNum);
		paramMap.put("searchType", searchType);
		paramMap.put("searchValue", searchValue);
		
		boardList = boardService.selectBoard(paramMap)
				.stream().map(BoardResponse::new).collect(Collectors.toList());
	
		for(BoardResponse board : boardList) {
			int commentCount = commentService.countComment( Long.valueOf(board.getSeq()));
			board.setCommentCount(String.valueOf(commentCount));
		}
		
		MemberVO loginMember = getLoginMember(request);
		PageMaker pageMaker = boardService.getPageMaker(Integer.parseInt(pageNum), Integer.parseInt(contentNum), paramMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("loginMember", loginMember);
		resultMap.put("boardList", boardList);
		resultMap.put("pageMaker", pageMaker);
		resultMap.put("searchType", searchType);
		resultMap.put("searchValue", searchValue);
		
		return resultMap;
	}
	
	@PostMapping("/board/loadArticle.do")
	public Map<String, Object> apiView(long seq, HttpServletRequest request) throws Exception {
		
		logger.info("==api loadArticle.do==");
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> file = boardService.selectFile(seq);
		BoardResponse board = new BoardResponse(boardService.selectBoardOne(seq));
		String likeCount = String.valueOf(likeService.likeCount(seq));
		MemberVO loginMember = getLoginMember(request);
		board.setLikeCount(likeCount);
		result.put("board", board);
		result.put("file", file);
		result.put("loginMember", loginMember);
		
		return result;
	}
	
	@PostMapping("/board/writerCheck.do")
	public String writerCheck(Long seq, HttpServletRequest request) throws Exception {
		
		logger.info("==api writerCheck.do==");
		
		MemberVO loginMember = getLoginMember(request);
		if(loginMember == null) {
			return "null";
		}
		String loginID = loginMember.getId();
		String boardID = boardService.selectBoardOne(seq).getWriter();
		
		return loginID.equals(boardID) ? "" : "none";
	}
	
	
	@PostMapping(value = "/board/delete.do")
	public int apidelete(Long seq) {
		
		logger.info("==api delete.do==");
		
		try {
			if(boardService.deleteBoard(seq) < 0) {
				return 402;
			}
			boardService.deleteFile(seq);
		}catch (DataIntegrityViolationException e) {
			logger.info("Message : "+e.getMessage());
			return 400;
		}catch (Exception e) {
			logger.info("Message : "+e.getMessage());
			return 401;
		}
		
		return 200;
	}
	
	@PostMapping("/board/writeSave.do")
	public boolean apiWriteSave(BoardVO vo, HttpServletRequest request, MultipartFile[] file) throws Exception{
		
		logger.info("==api writeSave.do==");
		
		MemberVO loginMember = getLoginMember(request);
		vo.setWriter(loginMember.getId());
		
		try {
			boardService.insertBoard(vo, file);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@PostMapping("/board/modifySave.do")
	public boolean apiModifySave(BoardVO vo, MultipartFile[] file, Long[] fileSeq) throws Exception{
		
		logger.info("==api modifySave.do==");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("board_seq", vo.getSeq());
		if(fileSeq != null) {
			ArrayList<Long> fileSeqList = new ArrayList<>(Arrays.asList(fileSeq));
			paramMap.put("fileSeq", fileSeqList);
		}else {
			paramMap.put("fileSeq", null);
		}
		try {
			boardService.modifyDelete(paramMap);
		} catch (Exception e) {
			logger.error("modify delete error : "+ e.getStackTrace());
		}
		
		
		BoardVO board = boardService.selectBoardOne(vo.getSeq());
		board.setTitle(vo.getTitle());
		board.setContents(vo.getContents());
		board.setLockCheck(vo.getLockCheck());
		board.setCategory(vo.getCategory());
		
		try {
			boardService.updateBoard(board, file);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	

	@PostMapping("/board/like.do")
	public int apiLike(long seq, HttpServletRequest request) throws Exception{
		
		logger.info("==api like.do==");
		
		BoardVO board = boardService.selectBoardOne(seq);
		MemberVO member = getLoginMember(request);
		LikeVO like = new LikeVO(board, member);
		LikeVO findLike = likeService.selectLikeCheck(like);
		if(findLike == null) {
			try {
				likeService.insertLike(like);
			}catch (Exception e) {
				e.printStackTrace();
				return 401;
			}
			return 200;
		}else {
			return 400;
		}
		
	}
	
	
	
	@RequestMapping(value = "/fileDown")
	public void fileDown(long seq, HttpServletResponse response) throws Exception {
		
		logger.info("==api fileDown==");
		
		Map<String, Object> resultMap = boardService.selectFileInfo(seq);
		String saveFileName = (String) resultMap.get("SAVE_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
		
		
		byte filebyte[] = FileUtils.readFileToByteArray(
				new File("C:"+File.separator+"Users"+File.separator+"wdfall"+File.separator+"eclipse-workspace"+File.separator
				+"windfall"+File.separator+"dashboard_v10"+File.separator+"file_dir"+File.separator+saveFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(filebyte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(filebyte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	
	
	// ========member=============================
	
	
	
	@PostMapping("/memberSave.do")
	public boolean memeberSave(Model model, MemberVO vo){
		logger.info("==member save==");
		
		try {
			memberService.insertMember(vo);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@PostMapping("/login.do")
	public boolean login(MemberVO vo, HttpServletRequest res) throws Exception{
		logger.info("==login==");
		MemberVO loginMember = memberService.loginMember(vo);
		HttpSession session =  res.getSession();
		
		if(loginMember == null) {
			
			return false;
		
		}else {
			session.setAttribute("session", loginMember);

			return true;
		}
	}
	
	@GetMapping("/logout.do")
	public void logout(HttpSession session) throws Exception{
		logger.info("==logout==");

		session.invalidate();
	}
	
	
	
	private MemberVO getLoginMember(HttpServletRequest request) throws Exception {
		
		if(request.getSession().getAttribute("session") == null){
			return null;
		}
		MemberVO session = (MemberVO)request.getSession().getAttribute("session");
		MemberVO member = memberService.selectMember(session);
		
		return member;
	}
	
}
