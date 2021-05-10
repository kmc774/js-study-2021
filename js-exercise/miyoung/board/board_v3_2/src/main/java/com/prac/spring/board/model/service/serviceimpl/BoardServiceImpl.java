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
	
	// ������ ������ ����
	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	
	// ��ü ����Ʈ ��ȸ
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



	
	// �Խñ� ���� �ҷ�����
	@Override
	public Board viewDetail(int bdIdx) {
		
		return boardRepository.viewDetail(bdIdx);
	}

	
	// �Խñ� ����ϱ�
	@Override
	public int insertBoard(Board board) {
		
		return boardRepository.insertBoard(board);
	}
	
	
	// �Խñ� �����ϱ�
	@Override
	public int updateBoard(Board board) {
		
		return boardRepository.updateBoard(board);
	}
	
	
	// �Խñ� �����ϱ�
	@Override
	public int deleteBoard(int bdIdx) {
		
		return boardRepository.deleteBoard(bdIdx);
	}


		
}
