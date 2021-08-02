package com.mi.board.controller;


import com.mi.board.model.dto.Board;
import com.mi.board.model.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String listBoard (@RequestParam(defaultValue= "1") int page , Model model) {
        model.addAllAttributes(boardService.selectBoardList(page));
        return "/board/board_list";
    }


    /**
     * 게시글 내용 보기
     * @param bdIdx
     * @param model
     * @return
     */
    @GetMapping("/view")
    public String detailBoard (int bdIdx, Model model) {
        Board board = boardService.selectBoardDetail(bdIdx);
        model.addAttribute("board", board);
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
     * 주제별 검색하기
     * @param type
     * @param keyword
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String searchBoard ( String type
                              , String keyword
                              , Model model) {

        model.addAllAttributes(boardService.selectSearchList(type , keyword));

        return "/board/board_list";
    }

}
