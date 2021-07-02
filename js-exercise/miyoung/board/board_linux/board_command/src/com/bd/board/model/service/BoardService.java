package com.bd.board.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bd.board.model.dao.BoardDao;
import com.bd.board.model.vo.BoardVO;

public class BoardService {
	
	
	BoardDao boardDao = new BoardDao();
	
	public List<BoardVO> boardAllList(){
		Connection conn = boardDao.getConnection();
		return boardDao.boardAllList(conn);
	}
	
	
	
	public List<BoardVO> boardList(int pageNum){
		Connection conn = boardDao.getConnection();
		Map<String,Object> numMap = new HashMap<String, Object>();
		int listNum = 10; // 보여주기 원하는 목록 갯수
		int startNum = pageNum * listNum; // 목록 시작 번호
		int endNum = (pageNum * listNum) + listNum; // 목록 끝 번호
		numMap.put("listNum", listNum);
		numMap.put("startNum", startNum);
		numMap.put("endNum", endNum);
		
		return boardDao.boardList(conn, numMap);
	}
	
	
	
	public BoardVO boardDetail(int seq){
		Connection conn = boardDao.getConnection();
		return boardDao.boardDetail(conn, seq);
	}

	
	
}
