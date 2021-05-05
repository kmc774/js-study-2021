package com.windfall.cereal.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.windfall.cereal.dto.BoardVO;
import com.windfall.cereal.dto.MemberVO;
import com.windfall.cereal.page.PageMaker;
import com.windfall.cereal.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/board", method = {RequestMethod.GET, RequestMethod.POST})
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String list(@RequestParam(defaultValue = "1") String pageNum, 
						@RequestParam(defaultValue = "10") String contentNum, Model model) throws Exception {
		logger.info("==list.do==");	
				
		return "list";
	}

	
	@GetMapping("/write.do")
	public String write(Model model, 
			@RequestParam(required = false)String seq) throws NumberFormatException, Exception {
		logger.info("==boardWrite==");
		String originSeq = null;
		String groupOrder = null;
		String groupLayer = null;
		if(seq != null) {
			BoardVO originBoard = service.selectBoardOne(Long.valueOf(seq));
			originSeq = String.valueOf(originBoard.getOriginSeq());
			groupOrder = String.valueOf(originBoard.getGroupOrder()+1);
			groupLayer = String.valueOf(originBoard.getGroupLayer()+1);
			model.addAttribute("replyCount", originBoard.getGroupLayer());
			model.addAttribute("referencedSeq", seq);
		}
		
		model.addAttribute("originSeq", originSeq);
		model.addAttribute("groupOrder", groupOrder);
		model.addAttribute("groupLayer", groupLayer);
		
		return "write";
	}
	
	
	@PostMapping("/view.do/{seq}")
	public String view(Model model, @PathVariable long seq) throws Exception{
		logger.info("==view.do==");
		int result = service.incCount(seq);
		model.addAttribute("seq", seq);
		return "view";
	}
	
	
	@GetMapping("/modify.do/{seq}")
	public String modify(Model model, @PathVariable("seq") long seq) throws Exception{
		logger.info("==board modify==");

		model.addAttribute("seq", seq);
		
		return "modify";
	}


	
	
    @RequestMapping("/boardInsert")
    public ModelAndView boardInsert(MultipartFile[] file) throws Exception {
        ModelAndView mav = new ModelAndView("redirect:/board/boardList");
        //boardService.insertBoard(commandMap);
        for(int i=0; i<file.length; i++) {
            logger.info("================== file start ==================");
            logger.info("파일 이름: "+file[i].getName());
            logger.info("파일 실제 이름: "+file[i].getOriginalFilename());
            logger.info("파일 크기: "+file[i].getSize());
            logger.info("content type: "+file[i].getContentType());
            logger.info("================== file   END ==================");
        }
        return mav;
    }

	
	
//	@GetMapping("/testDataInsert")
//	public String testDateInsert() throws Exception{
//		BoardVO vo = new BoardVO();
//		for(int i = 1; i <= 125; i++) {
//			vo.setTitle("testTitle ..."+i);
//			vo.setContents("testContents ..."+i);
//			vo.setWriter("user01");
//			service.insertBoard(vo);
//		}
//		
//		return "redirect:/list";
//	}
	
	
	
	
	
	
	
	
}
