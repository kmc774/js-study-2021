package com.vue.board.model.service.impl;

import com.vue.board.model.vo.Board;
import com.vue.board.model.repository.BoardRepository;
import com.vue.board.model.service.BoardService;
import com.vue.board.model.vo.BoardComment;
import com.vue.util.file.FileUtil;
import com.vue.util.file.FileVo;
import com.vue.util.paging.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {


    private BoardRepository boardRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 생성자 의존성 주입 방식 사용
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    @Override
    public int selectBoardCnt(String type, String keyword) { return boardRepository.selectBoardCnt(type, keyword);
    }

    @Override
    public List<Board> selectBoardList(Paging paging) {
        return boardRepository.selectBoardList(paging);
    }

    @Override
    public int insertBoard(Board board){ // void로 처리 --  exception으로 확인하는 방법

        // SQL문 결과로 bdIdx가 객체에 담겨진다.
        int resBoard = boardRepository.insertBoard(board);
        int bdIdx = board.getBdIdx();
        String res;
        res = resBoard > 0 ? "게시글 업로드 성공" : "게시글 업로드 실패"; // 게시글 업로드 로그 찍어주기
        logger.info(res);

        return bdIdx;
    }

    public int insertFiles(List<MultipartFile> files , int bdIdx){ // void로 처리 --  exception으로 확인하는 방법
        int result = 0;
        if(files != null){
            try {
                List<FileVo> fileList = null;
                fileList = FileUtil.filesUpload(files,bdIdx);
                for (FileVo fileVo : fileList ) { // 들어온 파일 내역만큼 for문 돌려주기
                    boardRepository.insertFile(fileVo);
                    result ++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public Board selectBoardDetail(int bdIdx) {
        Board board = boardRepository.selectBoardDetail(bdIdx);
        return board;
    }

    @Override
    public List<FileVo> selectAttachedFiles(int typeIdx) { return boardRepository.selectAttachedFiles(typeIdx); }

    @Override
    public List<FileVo> selectFiles(int fileIdx) {
        return boardRepository.selectFiles(fileIdx);
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
    public int deleteAttachedFile(int typeIdx) {  return boardRepository.deleteAttachedFile(typeIdx); }

    @Override
    public int deleteFile(int fileIdx) {
        return boardRepository.deleteFile(fileIdx);
    }

    @Override
    public int addComment(int bdIdx , String bdComment) { return boardRepository.addComment(bdIdx , bdComment); }

    @Override
    public void updateComment(int comIdx, String bdComment) { boardRepository.updateComment(comIdx , bdComment ); }

    @Override
    public List<BoardComment> selectComment(int bdIdx) { return boardRepository.selectComment(bdIdx); }

    @Override
    public void deleteComment(int comIdx) { boardRepository.deleteComment(comIdx); }

    @Override
    public int countLike(int bdIdx) { return boardRepository.countLike(bdIdx); }

    @Override
    public void countView(int bdIdx) { boardRepository.countView(bdIdx);  }


}