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
	
	
	// ������ ������ ����
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
		
	}
	
	
	
	// �Խ��� ����Ʈ ����
	@GetMapping("view/list")
	public String viewList(@RequestParam(defaultValue = "1") int page
							, Model model){
		
		model.addAllAttributes(boardService.viewList(page));
		return "board/list";
	}
	
	
	
	// �Խ��� ��������� �̵�
	@GetMapping("write")
	public String boadrWrite() {
		
		return "board/write";
	}
	
	
	
	// �Խñ� ����ϱ�
	@GetMapping("writeSave.do")
	public String saveBoard(@ModelAttribute @Valid Board board
							, BindingResult res
							, Model model) {
		
		
		if(res.hasErrors()) {
			System.out.println("���� ���� �߻�_���");
			return "board/write";
		}else {
			int result = boardService.insertBoard(board);
			
			if(result > 0) {
				model.addAttribute("alertMsg","�Խñ��� ��ϵǾ����ϴ�.");
				model.addAttribute("url", "view/list");
			}else {
				model.addAttribute("alertMsg","�Խñ� ��Ͽ� �����߽��ϴ�.");
				model.addAttribute("url", "view/list");
			}

			return "common/result";
		}	
	}
	
	
	
	
	// �Խñ� �ڼ��� ����
	@GetMapping("view/detail")
	public String viewDetail(int bdIdx, Model model) {
		
		
		Board board = boardService.viewDetail(bdIdx);
		
		//�ش� board�� �����ϸ� model�� ����
		if(board != null) {
			model.addAttribute("board", board);
		}

		return "board/view";
	}
	
	
	
	// �Խñ� �����ϱ�
	@GetMapping("view/delete")
	public String deleteBoard(int bdIdx, Model model) {
		int result = boardService.deleteBoard(bdIdx);
		
		if(result > 0) {
			model.addAttribute("alertMsg","�Խñ��� �����Ǿ����ϴ�.");
			model.addAttribute("url","list");
		}else {
			model.addAttribute("alertMsg","�Խñ��� �������� �ʾҽ��ϴ�.");
			model.addAttribute("url","list");
		}
		
		return "common/result";
	}
	
	
	
	
	// �Խñ� ���������� ����
	@GetMapping("modify")
	public String modifyForm(int bdIdx, Model model) {
		
		Board board = boardService.viewDetail(bdIdx);
		
		//�ش� board�� �����ϸ� model�� ����
		if(board != null) {
			model.addAttribute("board", board);
		}else {
			model.addAttribute("alertMsg","�ش� �Խñ��� �������� �ʽ��ϴ�.");
			model.addAttribute("url","view/list");
		}

		return "board/modify";
	}
	
	
	
	
	// �Խñ� �����ϱ�
	@GetMapping("modifySave")
	public String modifyBoard(@ModelAttribute @Valid Board board
							  , BindingResult res
							  , Model model) {
		
		if(res.hasErrors()) {
			System.out.println("��������߻�_����");
			model.addAttribute("url", "/board/modify?bdIdx=" + board.getBdIdx());
			model.addAttribute("alertMsg", "����, ������ �Է��ϼ���.");
			return "common/result";
			
		}else {
			Date today = new Date(); // ������Ʈ ��¥ ����
			SimpleDateFormat updateDate = new SimpleDateFormat("yy/MM/dd");
			board.setUpdateDate(updateDate.format(today)); //���� ��¥ ��ȯ�� = String
			
			int result = boardService.updateBoard(board); // boardService �Ѱ��ֱ�
			
			if(result > 0) {
				model.addAttribute("alertMsg","�Խñ��� �����Ǿ����ϴ�.");
				model.addAttribute("url", "view/detail?bdIdx=" + board.getBdIdx());
			}else {
				model.addAttribute("alertMsg","�Խñ��� �������� �ʾҽ��ϴ�.");
				model.addAttribute("url", "view/detail?bdIdx=" + board.getBdIdx());
			}
			
			return "common/result";
		}
	}
}
