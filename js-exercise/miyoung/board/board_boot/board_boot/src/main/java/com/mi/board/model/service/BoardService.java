package com.mi.board.model.service;

import com.mi.board.model.dto.Board;
import com.mi.board.model.repository.BoardRepository;
import com.mi.util.file.FileVo;
import com.mi.util.paging.Criteria;
import com.mi.util.paging.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface BoardService {

    int selectBoardCnt(String type , String keyword);

    List<Board> selectBoardList(Paging paging);

    boolean insertBoard(Board board, List<MultipartFile> files);

    Board selectBoardDetail(int bdIdx);

    List<FileVo> selectFiles(int bdIdx);

    int updateBoard(Board board);

    int deleteBoard(int bdIdx);

    int deleteFile(String fileIdx);


}