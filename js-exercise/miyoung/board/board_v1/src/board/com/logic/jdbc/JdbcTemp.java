package board.com.logic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemp {
	
	// JDBC가 굳이 객체로 만들어져야 하는지에 대한 고찰 필요,,!
	// Dao가 여러개인 경우엔 객체로 만들어서 사용할 수 있지만 트랜잭션을 따로 빼서 사용 하는 경우도 있을 것
	
	
	
	

	private static JdbcTemp instance;
	
	
	public JdbcTemp() { 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	
	 public static JdbcTemp getInstance() {
	      if(instance == null) {
	         instance = new JdbcTemp();
	      }
	      
	      return instance;
	   }
	   
	 
	 
	 
	 
	   public Connection getConnection() {
	      
	      String url = "jdbc:oracle:thin:@localhost:1521:xe";
	      String user = "SYSTEM";
	      String password = "1*aL201210380";
	      
	      Connection conn = null;
	      
	      try {
	    	  //url, user, password를 String 변수로 만들어 넣어줌
	         conn = DriverManager.getConnection(url,user,password);
	         //직접 commit 관리할 것
	         conn.setAutoCommit(false);
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      
	      return conn;
	   }
	   
	   
	   
	   
	   //commit 
	   public void commit(Connection conn) {
	      try {
	         conn.commit();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	   
	   
	   
	   
	   
	   //rollback 
	   public void rollback(Connection conn) {
	      try {
	         conn.rollback();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	   
	   
	   
	   
	   
	   public void close(Connection conn) {
	      try {
	         if(conn != null && !conn.isClosed()) {
	            conn.close();
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	   
	   
	   
	   
	   
	   
	   
	   public void close(ResultSet rset) {
	      try {
	         if(rset != null && !rset.isClosed()) {
	            rset.close();
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	   
	   
	   
	   
	   
	   public void close(Statement stmt) {
	      try {
	         if(stmt != null && !stmt.isClosed()) {
	            stmt.close();
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	   
	   
	   
	   
	 
	   
	   
	   
	   public void close(ResultSet rset, Statement stmt) {
	      close(rset);
	      close(stmt);
	   }
	   
	   
	   

	 
	}