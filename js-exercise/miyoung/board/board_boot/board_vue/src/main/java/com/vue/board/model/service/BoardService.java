package com.vue.board.model.service;

import com.vue.board.model.vo.Board;
import com.vue.util.file.FileVo;
import com.vue.util.paging.Paging;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BoardService {

    int selectBoardCnt(String type , String keyword);

    List<Board> selectBoardList(Paging paging);

    int insertBoard(Board board);

    int insertFiles(List<MultipartFile> files, int bdIdx);

    Board selectBoardDetail(int bdIdx);

    List<FileVo> selectFiles(int typeIdx , int fileIdx);

    int updateBoard(Board board);

    int deleteBoard(int bdIdx);

    int deleteFile(int typeIdx , int fileIdx);


}