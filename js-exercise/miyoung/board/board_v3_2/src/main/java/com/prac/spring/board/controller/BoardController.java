package com.prac.spring.board.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prac.spring.board.model.service.BoardService;
import com.prac.spring.board.model.vo.Board;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private final BoardService boardService;
	
	
	// 생성자 의존성 주입
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
		
	}
	
	
	
	// 게시판 리스트 보기
	@RequestMapping(value="list", method=RequestMethod.GET)		
	public String viewList(@RequestParam(defaultValue = "1") int page // 페이징 값을 디폴트 1로 받아 오류 안뜨게 설정
							, Model model){
		
		model.addAllAttributes(boardService.viewList(page));
		return "board/list";
	}
	
	
	
	// 게시판 등록페이지 이동
	@RequestMapping(value="write", method=RequestMethod.GET)
	public String boadrWrite() {
		
		return "board/write";
	}
	
	
	
	// 게시글 등록하기
	@RequestMapping(value="writeSave.do", method=RequestMethod.GET)
	public String saveBoard(@ModelAttribute @Valid Board board
							, BindingResult res
							, Model model) {
		
		
		if(res.hasErrors()) {
			System.out.println("공백 오류 발생_등록");
			return "board/write";
		}else {
			int result = boardService.insertBoard(board);
			
			if(result > 0) {
				model.addAttribute("alertMsg","게시글이 등록되었습니다.");
				model.addAttribute("url", "list");
			}else {
				model.addAttribute("alertMsg","게시글 등록에 실패했습니다.");
				model.addAttribute("url", "list");
			}

			return "common/result";
		}	
	}
	
	
	
	
	// 게시글 자세히 보기
	@RequestMapping(value="view/{bdIdx}", method=RequestMethod.GET)
	public String viewDetail(@PathVariable int bdIdx, Model model) {
		Board board = boardService.viewDetail(bdIdx);
		
		//해당 board가 존재하면 model로 전송
		if(board != null) {
			model.addAttribute("board", board);
		} else {
			model.addAttribute("alertMsg","해당게시글이 존재하지 않습니다.");
			model.addAttribute("url","/board/list");
			return "common/result";
		}

		return "board/view";
	}
	
	
	
	// 게시글 삭제하기
	@RequestMapping(value="delete/{bdIdx}", method=RequestMethod.GET)
	public String deleteBoard(@PathVariable int bdIdx, Model model) {
		int result = boardService.deleteBoard(bdIdx);
		
		if(result > 0) {
			model.addAttribute("alertMsg","게시글이 삭제되었습니다.");
			model.addAttribute("url","/board/list");
		}else {
			model.addAttribute("alertMsg","해당 게시글이 존재하지 않습니다.");
			model.addAttribute("url","/board/list");
		}
		
		return "common/result";
	}
	
	
	
	
	// 게시글 수정페이지 가기
	@RequestMapping(value="modify/{bdIdx}", method=RequestMethod.GET)
	public String modifyForm(@PathVariable int bdIdx, Model model) {
		
		Board board = boardService.viewDetail(bdIdx);
		
		//해당 board가 존재하면 model로 전송
		if(board != null) {
			model.addAttribute("board", board);
		}else {
			model.addAttribute("alertMsg","해당 게시글이 존재하지 않습니다.");
			model.addAttribute("url","/board/list");
			return "common/result";
		}

		return "board/modify";
	}
	
	
	
	
	// 게시글 수정하기
	// 유효성 검사
	@RequestMapping(value="modifySave", method=RequestMethod.GET)
	public String modifyBoard(@ModelAttribute @Valid Board board
							  , BindingResult res
							  , Model model) {
		
		if(res.hasErrors()) {
			System.out.println("공백오류발생_수정");
			model.addAttribute("url", "/board/modify/" + board.getBdIdx());
			model.addAttribute("alertMsg", "제목, 내용을 입력하세요.");
			return "common/result";
			
		}else {
			Date today = new Date(); // 업데이트 날짜 포맷
			SimpleDateFormat updateDate = new SimpleDateFormat("yy/MM/dd");
			board.setUpdateDate(updateDate.format(today)); //현재 날짜 반환값 = String
			
			int result = boardService.updateBoard(board); // boardService 넘겨주기
			
			if(result > 0) {
				model.addAttribute("alertMsg","게시글이 수정되었습니다.");
				model.addAttribute("url", "view/" + board.getBdIdx());
			}else {
				model.addAttribute("alertMsg","게시글이 수정되지 않았습니다.");
				model.addAttribute("url", "view/" + board.getBdIdx());
			}
			
			return "common/result";
		}
	}
}
