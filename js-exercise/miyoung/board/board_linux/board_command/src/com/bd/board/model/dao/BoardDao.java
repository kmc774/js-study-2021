package com.bd.board.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bd.board.model.vo.BoardVO;

public class BoardDao {
	

	public Connection getConnection() {
		System.out.println("driver 연결 시작 !");
		String user = "root";
		String password = "1*al2238520";
		String url = "jdbc:mysql://localhost:3306/board";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			Class.forName("com.mysql.jdbc.Driver"); // mysql 사용
			System.out.println("DB 연결 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}
	 
	public List<BoardVO> boardAllList(Connection conn) {
		 List<BoardVO> boardAllList = new ArrayList<>();
		 PreparedStatement pstm = null;
		 ResultSet res =  null;
		 
		 String query =  " select seq, title, content, reg_date " + 
				 		 "   from board " + 
				 		 "  order by seq desc" ;
		 
		 try {
			 
			pstm = conn.prepareStatement(query);
			res = pstm.executeQuery();
			while(res.next()) {
				BoardVO boardVo = new BoardVO();
				boardVo.setSeq(res.getInt("seq"));
				boardVo.setTitle(res.getString("title"));
				boardVo.setContent(res.getString("content"));
				boardVo.setRegDate(res.getDate("reg_date"));
				boardAllList.add(boardVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstm.close();
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 return boardAllList;
	 }
	
	
	
	 
	 public List<BoardVO> boardList(Connection conn, Map<String, Object> numMap) {
		 List<BoardVO> boardList = new ArrayList<>();
		 PreparedStatement pstm = null;
		 ResultSet res =  null;
		 
		 String query =  " select @rownum := @rownum+1 AS RNUM , b.* " + 
				 		 "   from board b, (SELECT @rownum :=0) AS R " + 
				 		 "  order by b.seq desc" + 
				 		 "  limit " + numMap.get("startNum") + "," + numMap.get("listNum"); // startNum = '몇번째 숫자부터?' , listNum = '몇개의 목록을?'
		 
		 try {
			 
			pstm = conn.prepareStatement(query);
			res = pstm.executeQuery();
			while(res.next()) {
				BoardVO boardVo = new BoardVO();
				boardVo.setSeq(res.getInt("seq"));
				boardVo.setTitle(res.getString("title"));
				boardVo.setContent(res.getString("content"));
				boardVo.setRegDate(res.getDate("reg_date"));
				boardList.add(boardVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstm.close();
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 return boardList;
	 }

	
	 public BoardVO boardDetail(Connection conn , int seq){
		 PreparedStatement pstm = null;
		 ResultSet res = null;
		 BoardVO boardVo = new BoardVO();
		 String query = " select title " +
				 		" 	  	,content " +
				 		" 		,reg_date " +
				 		"   from board " +
				 		"  where seq = " +"\"" + seq + "\"";
		 try {
			 
				pstm = conn.prepareStatement(query);
				res = pstm.executeQuery();
				if(res.next()) {
				boardVo.setTitle(res.getString("title"));
				boardVo.setContent(res.getString("content"));
				boardVo.setRegDate(res.getDate("reg_date"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
						conn.close();
						pstm.close();
						res.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		 
		 return boardVo;
		 
	 }
	 
	 
}	