package com.mi.board.model.repository;

import com.mi.board.model.dto.Board;
import com.mi.util.paging.Paging;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardRepository {

     int insertBoard(Board board);
     int selectBoardCnt();
     int selectSearchBoardCnt(String type, String keyword);
     List<Board> selectBoardList(Paging page);
     Board selectBoardDetail(int bdIdx);
     List<Board> selectSearchList(String type, String keyword);

}
