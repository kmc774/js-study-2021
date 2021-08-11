package com.mi.board.model.service.impl;

import com.mi.board.model.dto.Board;
import com.mi.board.model.repository.BoardRepository;
import com.mi.board.model.service.BoardService;
import com.mi.util.paging.Criteria;
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
    public int selectBoardCnt(String type, String keyword){
        return boardRepository.selectBoardCnt(type, keyword);
    }

    @Override
    public Map<String, Object> selectBoardList(Criteria criteria) {
        Paging paging  = Paging.builder()
                        .criteria(criteria)
                        .total(boardRepository.selectBoardCnt(criteria.getType() , criteria.getKeyword()))
                        .build();
        Map<String, Object> commandMap = new HashMap<String, Object>();
        commandMap.put("paging", paging);
        commandMap.put("boardList", boardRepository.selectBoardList(paging)); //boardList가 담기게 된다.
        return commandMap;

    }

    @Override
    public boolean insertBoard(Board board) {
        int num = boardRepository.insertBoard(board);
        boolean result = num > 0 ? true : false;
        return result;
    }

    @Override
    public Board selectBoardDetail(String bdIdx) {
        return boardRepository.selectBoardDetail(bdIdx);
    }


    @Override
    public int updateBoard(Board board) {
        return boardRepository.updateBoard(board);
    }


    @Override
    public int deleteBoard(String bdIdx) { return boardRepository.deleteBoard(bdIdx); }


}