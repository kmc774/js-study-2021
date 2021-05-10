package com.prac.spring.board.model.service.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prac.spring.board.model.repository.BoardRepository;
import com.prac.spring.board.model.service.BoardService;
import com.prac.spring.board.model.vo.Board;
import com.prac.spring.common.file.FileUtil;
import com.prac.spring.common.file.FileVo;
import com.prac.spring.common.paging.Paging;


@Service
public class BoardServiceImpl implements BoardService{
	
	
	private final BoardRepository boardRepository;
	
	// 생성자 의존성 주입
	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	
	// 전체 리스트 조회
	@Override
	public Map<String, Object> viewList(int currentPage) {
		
		Paging paging = Paging.builder()
							  .currentPage(currentPage)
							  .blockCnt(5)
							  .cntPerPage(10)
							  .type("board")
							  .total(boardRepository.selectContentCnt())
							  .build();
				
		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("paging", paging);
		boardMap.put("boardList", boardRepository.selectBoardList(paging));
		
		
		return boardMap;
	}



	
	// 게시글 내역 불러오기
	@Override
	public Map<String, Object> viewDetail(int bdIdx) {
		
		Board board = boardRepository.viewDetail(bdIdx);
		List<FileVo> files = boardRepository.viewFile("b" + bdIdx);
		
		Map<String , Object> commandMap = new HashMap<String, Object>();
		commandMap.put("board", board);
		commandMap.put("files", files);
	
		return commandMap;
	}

	
	
	
	@Override
	public int updateCount(int bdIdx) {
		return boardRepository.updateCount(bdIdx);
	}


	
	
	
	// 게시글 등록하기
	@Override
	public int insertBoard(Board board, List<MultipartFile> files) {
		
		FileUtil fileUtil = new FileUtil();
		boardRepository.insertBoard(board); //게시판 저장
		
		try {
			List<FileVo> fileList = fileUtil.filesUpload(files);
			for (FileVo fileVo : fileList) {
				boardRepository.insertFile(fileVo); //반환된 리스트  insert
			}
			return '1';
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return '0';
		}
	}
	
	
	// 게시글 수정하기
	@Override
	public int updateBoard(Board board) {

		return boardRepository.updateBoard(board);
	}
	
	
	
	
	// 게시글 삭제하기
	@Override
	public int deleteBoard(int bdIdx) {
		
		return boardRepository.deleteBoard(bdIdx);
	}


	
	
	@Override
	public String checkRecommend(int bdIdx, String userId) {
		
		String res = null;
		
		if(boardRepository.checkRecommend(bdIdx, userId) > 0) { //기존에 추천한 내역이 있다면,
			res = "exit";
		}else if(boardRepository.checkRecommend(bdIdx, userId) < 1){ //추천한 내역이 없다면,
			int result = boardRepository.insertRecommend(bdIdx, userId);
			res = result > 0 ? "success" : "fail";
		}
		return res;
	}


	
		
}
