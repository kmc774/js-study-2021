package com.mi.board.controller;


import com.mi.board.model.dto.Board;
import com.mi.board.model.service.BoardService;
import com.mi.util.paging.Criteria;
import com.mi.util.paging.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/board")
public class boardController {

    private BoardService boardService;

    // 생성자 의존성 주입 방식 사용
    public boardController(BoardService boardService) {
        this.boardService = boardService;
    }


    /**
     * 리스트 불러오기 + 페이징
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String listBoard (  @ModelAttribute Criteria criteria
                              , Model model) {

        // 페이징 객체와 게시판 객체 model에 저장
        model.addAllAttributes(boardService.selectBoardList(criteria));
        return "/board/board_list";
    }


    /**
     * 게시글 게시글 보기
     * 주제별 게시글 보기
     * @param bdIdx
     * @param model
     * @return
     */
    @GetMapping("/view")
    public String detailBoard (@ModelAttribute Criteria criteria
                                , int bdIdx
                                , Model model) {
        Board board = boardService.selectBoardDetail(bdIdx);
        model.addAttribute("board", board);
        model.addAttribute("paging", criteria);
        return "/board/board_detail";
    }


    /**
     * 게시글 작성화면으로 이동하기
     * @return
     */
    @GetMapping("/goWrite")
    public String goWriteBoard () { return "/board/board_write"; }


    /**
     * 게시글 등록하기
     * @param board
     * @return
     */
    @PostMapping("/write")               //JSON으로 보냈기 때문에 Board 객체로 저장
    public @ResponseBody String WriteBoard (@RequestBody Board board) {

        boolean result = false;
        String title  = board.getTitle();
        String content = board.getContent();
        String userId = board.getUserId();
        if (title == null || title.isEmpty()){     return "title"; }
        if (content == null || content.isEmpty()){ return "content";}
        if (userId == null || userId.isEmpty()){   return "userId"; }

        result = boardService.insertBoard(board);

        return result == false ? "fail" : "success";
    }

    /**
     * 게시글 수정페이지 이동
     * @param bdIdx
     * @return
     */
    @GetMapping("/goUpdate")
    public String goModifyBoard (int bdIdx ,
                                 String page,
                                 String type,
                                 String keyword,
                                 @RequestParam(defaultValue= "10") int cntPerPage,
                                 @RequestParam(defaultValue = "desc") String filter,
                                 Model model){
        Board board = boardService.selectBoardDetail(bdIdx);
        if( page != null  && type != null && keyword != null){
            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("page", page);
            infoMap.put("type", type);
            infoMap.put("keyword", keyword);
            infoMap.put("cntPerPage", cntPerPage);
            infoMap.put("filter" , filter);
            model.addAttribute("infoMap", infoMap);
        }
        model.addAttribute("board", board);
        model.addAttribute("cntPerPage", cntPerPage);
        model.addAttribute("filter" , filter);
        return "/board/board_modify";
    }

    /**
     * 게시글 수정하기
     * @param board
     * @return
     */
    @PutMapping("/update")
    public @ResponseBody String updateBoard(@RequestBody Board board){
        String title   = board.getTitle();
        String content = board.getContent();
        if (title == null || title.isEmpty()){     return "title"; }
        if (content == null || content.isEmpty()){ return "content";}

        int result = boardService.updateBoard(board);
        return  result > 0 ? "success" : "fail";
    }


    @DeleteMapping("/delete")
    public @ResponseBody String deleteBoard(@RequestBody int bdIdx){
        int result = boardService.deleteBoard(bdIdx);
        return result > 0 ? "success" : "fail";
    }






}