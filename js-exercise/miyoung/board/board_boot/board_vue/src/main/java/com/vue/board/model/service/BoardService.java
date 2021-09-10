package com.vue.board.model.service;

import com.vue.board.model.vo.Board;
import com.vue.board.model.vo.BoardComment;
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

    List<FileVo> selectAttachedFiles(int typeIdx);

    List<FileVo> selectFiles(int fileIdx);

    int updateBoard(Board board);

    int deleteBoard(int bdIdx);

    int deleteAttachedFile(int typeIdx);

    int deleteFile(int fileIdx);

    int addComment(int bdIdx , String bdComment);

    void updateComment(int comIdx , String bcComment);

    List<BoardComment> selectComment(int bdIdx);

    void deleteComment(int comIdx);

    int countLike(int bdIdx);

    void countView(int bdIdx);


}