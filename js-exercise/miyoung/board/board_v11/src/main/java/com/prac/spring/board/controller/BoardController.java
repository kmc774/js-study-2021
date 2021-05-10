package com.prac.spring.board.controller;


import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prac.spring.board.model.service.BoardService;
import com.prac.spring.board.model.vo.Board;
import com.prac.spring.common.file.FileVo;
import com.prac.spring.common.setup.SetupAttribute;
import com.prac.spring.member.model.vo.MemberVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private final BoardService boardService;
	@Autowired
	private SetupAttribute setupAttribute;
	
	
	
	// 생성자 의존성 주입
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
		
	}
	
	
	
	
	@GetMapping("list")		
	public String viewList(@RequestParam(defaultValue = "1") int page // 페이징 값을 디폴트 1로 받아 오류 안뜨게 설정
							, Model model){
		
		model.addAllAttributes(boardService.viewList(page));
		return "board/list";
	}
	
	
	
	
	@GetMapping("write")
	public String boadrWrite() {
		
		return "board/write";
	}
	
	
	
	
	@PostMapping("writeSave.do")
	public String saveBoard(  @ModelAttribute @Valid Board board
							, @RequestParam List<MultipartFile> files
							, BindingResult res
							, Model model) {
		System.out.println(board);
		if(res.hasErrors()) {
			System.out.println("공백 오류 발생_등록");
			return null;
		}else {
			int result = boardService.insertBoard(board, files); //게시글, 파일 등록
			String complete = result > 0 ? "게시글이 등록되었습니다." : "게시글 등록에 실패했습니다."; //삼항연산자 사용으로 complete 출력
			model.addAttribute("complete", complete);
			model.addAllAttributes(boardService.viewList(1)); //페이지 값 명시해주
			return null;
		}	
	}
	
	
	
	
	@GetMapping("view/{bdIdx}")
	public String viewDetail(@PathVariable int bdIdx
							, Model model) {
		
		//Board Count Update문 실행
		boardService.updateCount(bdIdx);
		model.addAllAttributes(boardService.viewDetail(bdIdx));
		
		return "board/view";
	}
	
	
	/**
	 * 비공개 게시글 받기
	 * @param bdIdx
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("secretview/{bdIdx}")
	public String viewSecretDetail(@PathVariable int bdIdx
							, HttpSession session
							, Model model) {
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		String userId = member.getUserId();
		Board board = (Board)boardService.viewDetail(bdIdx).get("board");
		String bdUserId = board.getUserId();
		
		if(userId.equals(bdUserId)) {
			//Board Count Update문 실행
			boardService.updateCount(bdIdx);
			model.addAllAttributes(boardService.viewDetail(bdIdx));
		}else if(!userId.equals(bdUserId)) {
			model.addAttribute("complete", "접근할 수 없습니다.");
			return "/index";
		}
		
		return "board/view";
	}
	
	
	
	
	@GetMapping("delete/{bdIdx}")
	public String deleteBoard(@PathVariable int bdIdx, Model model) {
		int result = boardService.deleteBoard(bdIdx);
		
		String complete = result > 0 ? "게시글이 삭제되었습니다." : "게시글이 존재하지 않습니다.";
		model.addAttribute("complete", complete);
		model.addAllAttributes(boardService.viewList(1)); //list page값 명시
		
		return "/board/list";
	}
	
	
	
	
	
	@GetMapping("modify/{bdIdx}")
	public String modifyForm(@PathVariable int bdIdx
							, Model model) {
		
	
		Map<String, Object> boardMap = boardService.viewDetail(bdIdx);
		//해당 board가 존재하면 model로 전송
		if(boardMap != null) {
			model.addAllAttributes(boardMap);
		}else {
			setupAttribute.modelAllAttribute("해당 게시글이 존재하지 않습니다.", "/board/list", model);
		}
		return "board/modify";
	}
	
	
	
	
	
	@PostMapping("modifySave")
	public String modifyBoard(@ModelAttribute @Valid Board board
							  , BindingResult res
							  , Model model) {
		
	
		if(res.hasErrors()) {
			System.out.println("공백오류발생_수정");
			model.addAttribute("complete", "제목, 내용을 입력하세요.");
			return "board/modify";
		}else {
			Date today = new Date(); // 업데이트 날짜 포맷
			SimpleDateFormat updateDate = new SimpleDateFormat("yy/MM/dd");
			board.setUpdateDate(updateDate.format(today)); //현재 날짜 반환값 = String
			
			int result = boardService.updateBoard(board); // boardService 넘겨주기
			String complete  = result > 0 ? "게시글 수정이 완료되었습니다.":"게시글이 수정되지 않았습니다.";
			model.addAttribute("complete", complete);
			model.addAllAttributes(boardService.viewDetail(board.getBdIdx()));
			
			return "board/view";
		}
	}
	
	
	/**
	 * 추천 checked에 따라 추천데이터 입력
	 * @param dataMap
	 * @return
	 */
	@PostMapping("recommend")
	@ResponseBody
	public String recommendBoard(@RequestBody Map<String, String> dataMap
								, HttpSession session) {
		
		
		System.out.println(dataMap);
		int bdIdx = Integer.parseInt(dataMap.get("bdIdx"));
		String userId = dataMap.get("userId");
		String bdUserId = dataMap.get("bdUserId");
		String result = null;
		
		result = userId.equals(bdUserId)? "same" : boardService.checkRecommend(bdIdx , userId);
		
		return result;
	}
	
	
	
	/**
	 * file 다운로드  메소드
	 * @param file
	 * @return
	 */
	@GetMapping("download")
	public ResponseEntity downloadFile(@ModelAttribute FileVo file) { //FileVo에 해당 파라미터값들 매핑
		
		
				//ResponseEntity : 클라이언트의 HttpRequest를  포함하는 객체
				HttpHeaders headers = new HttpHeaders(); //ResponseEntity Header를 지정해주는 것이 중요
						//setContentDispositionFormData -> 문자열로 세팅하려면 인코딩 직접해야됨 -> 번거로움 줄이기 위해 ContentDispositon 사용
				headers.setContentDisposition(ContentDisposition 
														.builder("attachment") // attachment 형식
														.filename(file.getOriginFileName(), Charset.forName("utf-8")) // 파일이름과 인코딩타입 설정
														.build()); // buildg하기
												
				FileSystemResource resource = new FileSystemResource(file.getFullPath() + file.getRenameFileName());//String path 만 입력해주면 됨(전체경로 : 기본 디폴트 path + savePath + renameFileName)
							//상태코드 ok일 떄, hearders와 body 세팅
		return ResponseEntity.ok().headers(headers).body(resource); //Spring이 알아서 지정한 파일을 브라우저에게 보내줌
		
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
