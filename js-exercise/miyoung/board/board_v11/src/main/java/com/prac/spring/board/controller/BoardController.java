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
	
	
	
	// ������ ������ ����
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
		
	}
	
	
	
	
	@GetMapping("list")		
	public String viewList(@RequestParam(defaultValue = "1") int page // ����¡ ���� ����Ʈ 1�� �޾� ���� �ȶ߰� ����
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
			System.out.println("���� ���� �߻�_���");
			return null;
		}else {
			int result = boardService.insertBoard(board, files); //�Խñ�, ���� ���
			String complete = result > 0 ? "�Խñ��� ��ϵǾ����ϴ�." : "�Խñ� ��Ͽ� �����߽��ϴ�."; //���׿����� ������� complete ���
			model.addAttribute("complete", complete);
			model.addAllAttributes(boardService.viewList(1)); //������ �� �������
			return null;
		}	
	}
	
	
	
	
	@GetMapping("view/{bdIdx}")
	public String viewDetail(@PathVariable int bdIdx
							, Model model) {
		
		//Board Count Update�� ����
		boardService.updateCount(bdIdx);
		model.addAllAttributes(boardService.viewDetail(bdIdx));
		
		return "board/view";
	}
	
	
	/**
	 * ����� �Խñ� �ޱ�
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
			//Board Count Update�� ����
			boardService.updateCount(bdIdx);
			model.addAllAttributes(boardService.viewDetail(bdIdx));
		}else if(!userId.equals(bdUserId)) {
			model.addAttribute("complete", "������ �� �����ϴ�.");
			return "/index";
		}
		
		return "board/view";
	}
	
	
	
	
	@GetMapping("delete/{bdIdx}")
	public String deleteBoard(@PathVariable int bdIdx, Model model) {
		int result = boardService.deleteBoard(bdIdx);
		
		String complete = result > 0 ? "�Խñ��� �����Ǿ����ϴ�." : "�Խñ��� �������� �ʽ��ϴ�.";
		model.addAttribute("complete", complete);
		model.addAllAttributes(boardService.viewList(1)); //list page�� ���
		
		return "/board/list";
	}
	
	
	
	
	
	@GetMapping("modify/{bdIdx}")
	public String modifyForm(@PathVariable int bdIdx
							, Model model) {
		
	
		Map<String, Object> boardMap = boardService.viewDetail(bdIdx);
		//�ش� board�� �����ϸ� model�� ����
		if(boardMap != null) {
			model.addAllAttributes(boardMap);
		}else {
			setupAttribute.modelAllAttribute("�ش� �Խñ��� �������� �ʽ��ϴ�.", "/board/list", model);
		}
		return "board/modify";
	}
	
	
	
	
	
	@PostMapping("modifySave")
	public String modifyBoard(@ModelAttribute @Valid Board board
							  , BindingResult res
							  , Model model) {
		
	
		if(res.hasErrors()) {
			System.out.println("��������߻�_����");
			model.addAttribute("complete", "����, ������ �Է��ϼ���.");
			return "board/modify";
		}else {
			Date today = new Date(); // ������Ʈ ��¥ ����
			SimpleDateFormat updateDate = new SimpleDateFormat("yy/MM/dd");
			board.setUpdateDate(updateDate.format(today)); //���� ��¥ ��ȯ�� = String
			
			int result = boardService.updateBoard(board); // boardService �Ѱ��ֱ�
			String complete  = result > 0 ? "�Խñ� ������ �Ϸ�Ǿ����ϴ�.":"�Խñ��� �������� �ʾҽ��ϴ�.";
			model.addAttribute("complete", complete);
			model.addAllAttributes(boardService.viewDetail(board.getBdIdx()));
			
			return "board/view";
		}
	}
	
	
	/**
	 * ��õ checked�� ���� ��õ������ �Է�
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
	 * file �ٿ�ε�  �޼ҵ�
	 * @param file
	 * @return
	 */
	@GetMapping("download")
	public ResponseEntity downloadFile(@ModelAttribute FileVo file) { //FileVo�� �ش� �Ķ���Ͱ��� ����
		
		
				//ResponseEntity : Ŭ���̾�Ʈ�� HttpRequest��  �����ϴ� ��ü
				HttpHeaders headers = new HttpHeaders(); //ResponseEntity Header�� �������ִ� ���� �߿�
						//setContentDispositionFormData -> ���ڿ��� �����Ϸ��� ���ڵ� �����ؾߵ� -> ���ŷο� ���̱� ���� ContentDispositon ���
				headers.setContentDisposition(ContentDisposition 
														.builder("attachment") // attachment ����
														.filename(file.getOriginFileName(), Charset.forName("utf-8")) // �����̸��� ���ڵ�Ÿ�� ����
														.build()); // buildg�ϱ�
												
				FileSystemResource resource = new FileSystemResource(file.getFullPath() + file.getRenameFileName());//String path �� �Է����ָ� ��(��ü��� : �⺻ ����Ʈ path + savePath + renameFileName)
							//�����ڵ� ok�� ��, hearders�� body ����
		return ResponseEntity.ok().headers(headers).body(resource); //Spring�� �˾Ƽ� ������ ������ ���������� ������
		
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
