package com.prac.spring.board.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@GetMapping("view/list")
	public String viewList(@RequestParam(defaultValue = "1") int page
							, Model model){
		
		model.addAllAttributes(boardService.viewList(page));
		return "board/list";
	}
	
	
	
	// 게시판 등록페이지 이동
	@GetMapping("write")
	public String boadrWrite() {
		
		return "board/write";
	}
	
	
	
	// 게시글 등록하기
	@GetMapping("writeSave.do")
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
				model.addAttribute("url", "view/list");
			}else {
				model.addAttribute("alertMsg","게시글 등록에 실패했습니다.");
				model.addAttribute("url", "view/list");
			}

			return "common/result";
		}	
	}
	
	
	
	
	// 게시글 자세히 보기
	@GetMapping("view/detail")
	public String viewDetail(int bdIdx, Model model) {
		
		
		Board board = boardService.viewDetail(bdIdx);
		
		//해당 board가 존재하면 model로 전송
		if(board != null) {
			model.addAttribute("board", board);
		}

		return "board/view";
	}
	
	
	
	// 게시글 삭제하기
	@GetMapping("view/delete")
	public String deleteBoard(int bdIdx, Model model) {
		int result = boardService.deleteBoard(bdIdx);
		
		if(result > 0) {
			model.addAttribute("alertMsg","게시글이 삭제되었습니다.");
			model.addAttribute("url","list");
		}else {
			model.addAttribute("alertMsg","게시글이 삭제되지 않았습니다.");
			model.addAttribute("url","list");
		}
		
		return "common/result";
	}
	
	
	
	
	// 게시글 수정페이지 가기
	@GetMapping("modify")
	public String modifyForm(int bdIdx, Model model) {
		
		Board board = boardService.viewDetail(bdIdx);
		
		//해당 board가 존재하면 model로 전송
		if(board != null) {
			model.addAttribute("board", board);
		}else {
			model.addAttribute("alertMsg","해당 게시글이 존재하지 않습니다.");
			model.addAttribute("url","view/list");
		}

		return "board/modify";
	}
	
	
	
	
	// 게시글 수정하기
	@GetMapping("modifySave")
	public String modifyBoard(@ModelAttribute @Valid Board board
							  , BindingResult res
							  , Model model) {
		
		if(res.hasErrors()) {
			System.out.println("공백오류발생_수정");
			model.addAttribute("url", "/board/modify?bdIdx=" + board.getBdIdx());
			model.addAttribute("alertMsg", "제목, 내용을 입력하세요.");
			return "common/result";
			
		}else {
			Date today = new Date(); // 업데이트 날짜 포맷
			SimpleDateFormat updateDate = new SimpleDateFormat("yy/MM/dd");
			board.setUpdateDate(updateDate.format(today)); //현재 날짜 반환값 = String
			
			int result = boardService.updateBoard(board); // boardService 넘겨주기
			
			if(result > 0) {
				model.addAttribute("alertMsg","게시글이 수정되었습니다.");
				model.addAttribute("url", "view/detail?bdIdx=" + board.getBdIdx());
			}else {
				model.addAttribute("alertMsg","게시글이 수정되지 않았습니다.");
				model.addAttribute("url", "view/detail?bdIdx=" + board.getBdIdx());
			}
			
			return "common/result";
		}
	}
}
