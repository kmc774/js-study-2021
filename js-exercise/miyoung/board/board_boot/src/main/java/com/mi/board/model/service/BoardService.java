package com.mi.board.model.service;

import com.mi.board.model.dto.Board;
import com.mi.board.model.repository.BoardRepository;
import com.mi.util.paging.Criteria;
import com.mi.util.paging.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface BoardService {

    int selectBoardCnt(String type, String keyword);

    Map<String, Object> selectBoardList(Criteria criteria);

    boolean insertBoard(Board board);

    Board selectBoardDetail(int seq);

    int updateBoard(Board board);

    int deleteBoard(int bdIdx);


}