package com.prac.spring.board.model.service.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.prac.spring.board.model.repository.BoardRepository;
import com.prac.spring.board.model.service.BoardService;
import com.prac.spring.board.model.vo.Board;
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
		boardMap.put("boardList", boardRepository.selectAllList(paging));
		
		
		return boardMap;
	}



	
	// 게시글 내역 불러오기
	@Override
	public Board viewDetail(int bdIdx) {
		
		return boardRepository.viewDetail(bdIdx);
	}

	
	// 게시글 등록하기
	@Override
	public int insertBoard(Board board) {
		
		return boardRepository.insertBoard(board);
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


		
}
