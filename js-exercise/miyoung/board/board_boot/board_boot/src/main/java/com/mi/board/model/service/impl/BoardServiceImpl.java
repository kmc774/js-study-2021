package com.mi.board.model.service.impl;

import com.mi.board.model.dto.Board;
import com.mi.board.model.repository.BoardRepository;
import com.mi.board.model.service.BoardService;
import com.mi.util.file.FileUtil;
import com.mi.util.file.FileVo;
import com.mi.util.paging.Criteria;
import com.mi.util.paging.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {


    private BoardRepository boardRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 생성자 의존성 주입 방식 사용
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    @Override
    public int selectBoardCnt(String type, String keyword) {
        return boardRepository.selectBoardCnt(type, keyword);
    }

    @Override
    public List<Board> selectBoardList(Paging paging) {
        return boardRepository.selectBoardList(paging);
    }

    @Override
    public boolean insertBoard(Board board, List<MultipartFile> files) { // void로 처리 --  exception으로 확인하는 방법

        // SQL문 결과로 bdIdx가 객체에 담겨진다.
        int resBoard = boardRepository.insertBoard(board);
        String res;
        if (files != null) {
            try {
                List<FileVo> fileList = null;
                fileList = FileUtil.filesUpload(files, board.getBdIdx());
                for (FileVo fileVo : fileList) { // 들어온 파일 내역만큼 for문 돌려주기
                    boardRepository.insertFile(fileVo);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        boolean result = resBoard > 0 ? true : false; // 게시글 게시 여부에 따라 result값 설정
        res = resBoard > 0 ? "게시글 업로드 성공" : "게시글 업로드 실패"; // 게시글 업로드 로그 찍어주기
        logger.info(res);

        return result;
    }

    @Override
    public Board selectBoardDetail(int bdIdx) {
        Board board = boardRepository.selectBoardDetail(bdIdx);
        return board;
    }

    @Override
    public List<FileVo> selectFiles(int bdIdx) {
        return boardRepository.selectFiles(bdIdx);
    }


    @Override
    public int updateBoard(Board board) {
        return boardRepository.updateBoard(board);
    }

    @Override
    public int deleteBoard(int bdIdx) {
        return boardRepository.deleteBoard(bdIdx);
    }

    @Override
    public int deleteFile(String fileIdx) {
        return boardRepository.deleteFile(fileIdx);
    }


}