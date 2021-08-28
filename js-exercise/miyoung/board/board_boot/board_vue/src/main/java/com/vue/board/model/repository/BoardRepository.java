package com.vue.board.model.repository;

import com.vue.board.model.vo.Board;
import com.vue.util.file.FileVo;
import com.vue.util.paging.Paging;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardRepository {

     int selectBoardCnt(String type , String keyword);
     List<Board> selectBoardList(Paging page);
     int insertBoard(Board board);
     int insertFile(FileVo files);
     Board selectBoardDetail(int bdIdx);
     List<FileVo> selectFiles(int typeIdx , int fileIdx);
     int updateBoard(Board board);
     int deleteBoard(int bdIdx);
     int deleteFile(int typeIdx , int fileIdx);

}