package com.mi.board.model.repository;

import com.mi.board.model.dto.Board;
import com.mi.util.file.FileVo;
import com.mi.util.paging.Paging;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface BoardRepository {

    int selectBoardCnt(String type, String keyword);

    List<Board> selectBoardList(Paging page);

    int insertBoard(Board board);

    int insertFile(FileVo files);

    Board selectBoardDetail(int bdIdx);

    List<FileVo> selectFiles(int bdIdx);

    int updateBoard(Board board);

    int deleteBoard(int bdIdx);

    int deleteFile(String fileIdx);

}