package com.mi.board.model.service.impl;

import com.mi.board.model.dto.Board;
import com.mi.board.model.repository.BoardRepository;
import com.mi.board.model.service.BoardService;
import com.mi.util.paging.Paging;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {


    private BoardRepository boardRepository;

    // 생성자 의존성 주입 방식 사용
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public boolean insertBoard(Board board) {
        boolean result = false;
        int num = boardRepository.insertBoard(board);
        result = num > 0 ? true : false;
        return result;
    }

    @Override
    public int selectBoardCnt(){
        return boardRepository.selectBoardCnt();
    }

    @Override
    public Map<String, Object> selectBoardList(int currentPage) {
        Paging paging = Paging.builder()
                        .currentPage(currentPage)                // 현재 페이지
                        .blockCnt(5)                             // 한 줄에 표시될 블록수
                        .cntPerPage(10)                          // 페이지당 게시물 수
                        .type("board")                           // 페이징 처리할 항목
                        .total(boardRepository.selectBoardCnt()) // 전체 게시물 수
                        .build();
        Map<String, Object> commandMap = new HashMap<String, Object>();
        commandMap.put("paging", paging);
        commandMap.put("boardList", boardRepository.selectBoardList(paging)); //boardList가 담기게 된다.

        return commandMap;

    }

    @Override
    public Board selectBoardDetail(int bdIdx) {
        return boardRepository.selectBoardDetail(bdIdx);
    }



    @Override
    public Map<String, Object> selectSearchList( String type , String keyword ) {
        Map<String, Object> commandMap = new HashMap<>();
        int total = boardRepository.selectSearchBoardCnt(type, keyword);
        if(total > 0) {
            Paging paging = Paging.builder()
                            .currentPage(1)                          // 현재 페이지
                            .blockCnt(5)                             // 한 줄에 표시될 블록수
                            .cntPerPage(10)                          // 페이지당 게시물 수
                            .type("board")                           // 페이징 처리할 항목
                            .total(total)                            // 전체 게시물 수
                            .searchType(type)
                            .keyword(keyword)
                            .build();
          List<Board> boardList = boardRepository.selectSearchList(type , keyword);
          commandMap.put("paging" , paging);
          commandMap.put("boardList", boardList);
          return commandMap;
         }
        commandMap.put("paging" , null);
        commandMap.put("boardList", null);
        return commandMap;
    }


}
