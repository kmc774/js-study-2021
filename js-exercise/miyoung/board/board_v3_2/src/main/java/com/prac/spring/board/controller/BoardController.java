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
	
	
	// ������ ������ ����
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
		
	}
	
	
	
	// �Խ��� ����Ʈ ����
	@RequestMapping(value="list", method=RequestMethod.GET)		
	public String viewList(@RequestParam(defaultValue = "1") int page // ����¡ ���� ����Ʈ 1�� �޾� ���� �ȶ߰� ����
							, Model model){
		
		model.addAllAttributes(boardService.viewList(page));
		return "board/list";
	}
	
	
	
	// �Խ��� ��������� �̵�
	@RequestMapping(value="write", method=RequestMethod.GET)
	public String boadrWrite() {
		
		return "board/write";
	}
	
	
	
	// �Խñ� ����ϱ�
	@RequestMapping(value="writeSave.do", method=RequestMethod.GET)
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
				model.addAttribute("url", "list");
			}else {
				model.addAttribute("alertMsg","�Խñ� ��Ͽ� �����߽��ϴ�.");
				model.addAttribute("url", "list");
			}

			return "common/result";
		}	
	}
	
	
	
	
	// �Խñ� �ڼ��� ����
	@RequestMapping(value="view/{bdIdx}", method=RequestMethod.GET)
	public String viewDetail(@PathVariable int bdIdx, Model model) {
		Board board = boardService.viewDetail(bdIdx);
		
		//�ش� board�� �����ϸ� model�� ����
		if(board != null) {
			model.addAttribute("board", board);
		} else {
			model.addAttribute("alertMsg","�ش�Խñ��� �������� �ʽ��ϴ�.");
			model.addAttribute("url","/board/list");
			return "common/result";
		}

		return "board/view";
	}
	
	
	
	// �Խñ� �����ϱ�
	@RequestMapping(value="delete/{bdIdx}", method=RequestMethod.GET)
	public String deleteBoard(@PathVariable int bdIdx, Model model) {
		int result = boardService.deleteBoard(bdIdx);
		
		if(result > 0) {
			model.addAttribute("alertMsg","�Խñ��� �����Ǿ����ϴ�.");
			model.addAttribute("url","/board/list");
		}else {
			model.addAttribute("alertMsg","�ش� �Խñ��� �������� �ʽ��ϴ�.");
			model.addAttribute("url","/board/list");
		}
		
		return "common/result";
	}
	
	
	
	
	// �Խñ� ���������� ����
	@RequestMapping(value="modify/{bdIdx}", method=RequestMethod.GET)
	public String modifyForm(@PathVariable int bdIdx, Model model) {
		
		Board board = boardService.viewDetail(bdIdx);
		
		//�ش� board�� �����ϸ� model�� ����
		if(board != null) {
			model.addAttribute("board", board);
		}else {
			model.addAttribute("alertMsg","�ش� �Խñ��� �������� �ʽ��ϴ�.");
			model.addAttribute("url","/board/list");
			return "common/result";
		}

		return "board/modify";
	}
	
	
	
	
	// �Խñ� �����ϱ�
	// ��ȿ�� �˻�
	@RequestMapping(value="modifySave", method=RequestMethod.GET)
	public String modifyBoard(@ModelAttribute @Valid Board board
							  , BindingResult res
							  , Model model) {
		
		if(res.hasErrors()) {
			System.out.println("��������߻�_����");
			model.addAttribute("url", "/board/modify/" + board.getBdIdx());
			model.addAttribute("alertMsg", "����, ������ �Է��ϼ���.");
			return "common/result";
			
		}else {
			Date today = new Date(); // ������Ʈ ��¥ ����
			SimpleDateFormat updateDate = new SimpleDateFormat("yy/MM/dd");
			board.setUpdateDate(updateDate.format(today)); //���� ��¥ ��ȯ�� = String
			
			int result = boardService.updateBoard(board); // boardService �Ѱ��ֱ�
			
			if(result > 0) {
				model.addAttribute("alertMsg","�Խñ��� �����Ǿ����ϴ�.");
				model.addAttribute("url", "view/" + board.getBdIdx());
			}else {
				model.addAttribute("alertMsg","�Խñ��� �������� �ʾҽ��ϴ�.");
				model.addAttribute("url", "view/" + board.getBdIdx());
			}
			
			return "common/result";
		}
	}
}
