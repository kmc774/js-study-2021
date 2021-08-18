package com.mi.board.model.repository;

import com.mi.board.model.dto.Board;
import com.mi.util.paging.Paging;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardRepository {

     int selectBoardCnt(String type , String keyword);
     List<Board> selectBoardList(Paging page);
     int insertBoard(Board board);
     Board selectBoardDetail(String bdIdx);
     int updateBoard(Board board);
     int deleteBoard(String bdIdx);

}