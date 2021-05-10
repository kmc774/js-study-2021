package board.com.board.model.service;

import java.sql.Connection;
import java.util.List;

import board.com.board.model.dao.BoardDao;
import board.com.board.model.vo.Board;
import board.com.logic.jdbc.JdbcTemp;

public class BoardService {

	JdbcTemp jd = JdbcTemp.getInstance();
//	BoardDao boardDao = new BoardDao();
	
	BoardDao boardDao = new BoardDao();
	
	
	
	// 게시글 리스트 가져오기
	public List<Board> boardList(){
		Connection conn = jd.getConnection();
		return  boardDao.boardList(conn);
	}



	
	
	
	
	// 게시글 업로드
	public int insertBoard(Board board) {
		
		Connection conn = jd.getConnection();
		int result = 0;

		try {
			result = boardDao.insertBoard(conn, board);
			jd.commit(conn);
			
		}catch (Exception e) {
			jd.rollback(conn);
			
		}finally {
			jd.close(conn);
		}

		return result;
	}
	
	
	
	
	
	// 게시글 상세보기
	public Board veiwDetail(int bdIdx) {
		
		Connection conn = jd.getConnection();
		Board board = null;
		
		try {
			
			board = boardDao.viewDetail(conn, bdIdx);
			jd.commit(conn);
			
		} catch (Exception e) {
			jd.rollback(conn);
		} finally {
			jd.close(conn);
		}
		
		return board;
		
	}
	
	// 게시글 수정하기
	public int modifyBoard(Board board) {
		
		Connection conn = jd.getConnection();
		int result = 0;

		try {
			result = boardDao.modifyBoard(conn, board);
			jd.commit(conn);
			
		}catch (Exception e) {
			jd.rollback(conn);
			
		}finally {
			jd.close(conn);
		}

		return result;
	}
	

	// 게시글 삭제하기
	public int deleteBoard(int bdIdx) {
		
		Connection conn = jd.getConnection();
		int result = 0;

		try {
			result = boardDao.deleteBoard(conn, bdIdx);
			jd.commit(conn);
			
		}catch (Exception e) {
			jd.rollback(conn);
			
		}finally {
			jd.close(conn);
		}

		return result;
	}

	
	
	
	
	
	
	
	
	
	
	

}
