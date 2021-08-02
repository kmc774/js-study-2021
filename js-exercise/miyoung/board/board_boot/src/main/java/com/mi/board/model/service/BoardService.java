package com.mi.board.model.service;

import com.mi.board.model.dto.Board;
import com.mi.board.model.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface BoardService {


        public boolean insertBoard(Board board);

        public int selectBoardCnt();

        public Map<String, Object> selectBoardList(int currentPage);

        public Board selectBoardDetail(int seq);

        public Map<String, Object> selectSearchList( String type , String keyword );



}
