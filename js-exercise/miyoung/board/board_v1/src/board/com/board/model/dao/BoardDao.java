package board.com.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.com.board.model.vo.Board;
import board.com.logic.jdbc.JdbcTemp;

public class BoardDao {
	
	
	JdbcTemp jd = JdbcTemp.getInstance();
	
	
	
	
	
	
	//게시글 리스트 가져오기
	public List<Board> boardList(Connection conn){
		
		List<Board> boardList  = new ArrayList<Board>();
		ResultSet rset = null;
		PreparedStatement pstm = null;
		
		String query = "select bd_idx, title, content from m_board";
		
		try {
			
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				
				board.setBdIdx(rset.getInt(1));
				board.setTitle(rset.getString(2));
				board.setContent(rset.getString(3));
				
				boardList.add(board);
			}
			

		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			jd.close(rset, pstm); //pstm, rset close
		}
		
		return boardList;
	}
	
	
	
	
	//게시글 등록하기
	public int insertBoard(Connection conn, Board board) {
	
		int res = 0;
		PreparedStatement pstm = null;
		String query = "insert into m_board(bd_idx, title, content) values (sc_bd_idx.nextval, ?, ?)";
		

		
		try {
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, board.getTitle());
			pstm.setString(2, board.getContent());
			
			res = pstm.executeUpdate(); //결과에 변경된 쿼리수 만큼 반환
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}finally{
			
			jd.close(pstm); // pstm close
		}

		return res;

	}
	
	
	
	
	
	
	// 게시글 내역 불러오기
	public Board viewDetail(Connection conn, int bdIdx) {
		
		Board board = new Board();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select bd_idx, title, content from m_board where bd_idx = ? ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bdIdx);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) { //쿼리를 실행해서 결과만큼 while문 반복
				
				board.setBdIdx(rset.getInt(1));
				board.setTitle(rset.getString(2));
				board.setContent(rset.getString(3));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			jd.close(rset, pstm);
		}
		
		return board;
	}
	
	
	
	
	
	//게시글 수정하기
	public int modifyBoard(Connection conn, Board board) {
		
		int res = 0;
		PreparedStatement pstm = null;
		String query = "update m_board set title = ? , content = ? where bd_idx = ? ";
		

		
		try {
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, board.getTitle());
			pstm.setString(2, board.getContent());
			pstm.setInt(3, board.getBdIdx());
			
			res = pstm.executeUpdate(); //결과에 변경된 쿼리수 만큼 반환
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}finally{
			
			jd.close(pstm); // pstm close
		}

		return res;

	}
	
	
	
	
	
	//게시글 삭제하기
	public int deleteBoard(Connection conn, int bdIdx) {
		
		int res = 0;
		PreparedStatement pstm = null;
		String query = "delete from m_board where bd_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1,bdIdx);
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		} finally {
			  jd.close(pstm);
			
		
		}
		
		
		return res;
	}
	
	
	
	

}
