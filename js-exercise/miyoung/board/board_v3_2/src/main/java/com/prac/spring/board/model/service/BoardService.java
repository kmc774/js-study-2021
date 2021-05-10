package com.prac.spring.board.model.service;


import java.util.Map;

import com.prac.spring.board.model.vo.Board;

public interface BoardService {
	

	Map<String, Object> viewList(int currentPage);
	
	Board viewDetail(int bdIdx);
		
	int insertBoard(Board board);
	
	int updateBoard(Board board);
	
	int deleteBoard(int bdIdx);
}
