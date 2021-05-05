package com.windfall.cereal.controller.api;

import java.net.Authenticator.RequestorType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.windfall.cereal.controller.ApiController;
import com.windfall.cereal.dto.CommentVO;
import com.windfall.cereal.dto.MemberVO;
import com.windfall.cereal.service.CommentService;
import com.windfall.cereal.service.MemberService;

@RestController
@RequestMapping(value = {"/api/comment"}, method = {RequestMethod.GET, RequestMethod.POST})
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Inject
	MemberService memberService;
	@Inject
	CommentService commentService;
	
	
	
	@PostMapping("/save.do")
	public boolean save(CommentVO vo, HttpServletRequest request) throws Exception {
		
		logger.info("== api comment save ==");
		
		MemberVO member = getLoginMember(request);
		vo.setWriter(member.getId());
		
		try {
			commentService.insertComment(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	@PostMapping("/list.do")
	public List<CommentVO> list(long boardSeq, long commentReadCount) throws Exception{
		
		logger.info("== api comment list ==");

		Map<String, Long> paramMap = new HashMap<>();
		paramMap.put("board_seq", boardSeq);
		paramMap.put("commentReadCount", commentReadCount);
		List<CommentVO> commentList = commentService.selectCommentList(paramMap);
		
		return commentList;
	}
	
	@PostMapping("/modifySave.do")
	public boolean modifySave(CommentVO vo) throws Exception{
		
		logger.info("== api comment modify Save ==");
		
		try {
			commentService.updateComment(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	@PostMapping("/delete.do")
	public boolean delete(long seq) throws Exception{
		
		logger.info("== api comment delete ==");
		
		try {
			commentService.deleteComment(seq);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	
	
	private MemberVO getLoginMember(HttpServletRequest request) throws Exception {
		
		MemberVO session = (MemberVO)request.getSession().getAttribute("session");
		MemberVO member = memberService.selectMember(session);
		
		return member;
	}

	
//	@GetMapping("/testDateInsert")
//	public void testDateInsert() {
//		
//		for(int i=1; i <= 100; i++) {
//			CommentVO vo = new CommentVO();
//			vo.setWriter("user01");
//			vo.setContents("test comment comtents..."+i);
//			vo.setBoardSeq(166);
//			
//			try {
//				commentService.insertComment(vo);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
}
