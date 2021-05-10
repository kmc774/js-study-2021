package com.prac.spring.board.model.service;


import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.prac.spring.board.model.vo.Board;

public interface BoardService {
	

	Map<String, Object> viewList(int currentPage);
	
	Map<String, Object> viewDetail(int bdIdx);
		
	int insertBoard(Board board, List<MultipartFile> files);
	
	int updateBoard(Board board);
	
	int deleteBoard(int bdIdx);
	
	int updateCount(int bdIdx);
	
	String checkRecommend(int bdIdx, String userId);
	
	
	
	
	
}
