package com.bd.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.bd.board.model.dao.BoardDao;

public class mysqlConnectionTest {
	
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet res;

	public static void main(String[] args) {
		
		
		String user = "root";
		String password = "1*al2238520";
		String url = "jdbc:mysql://localhost:3306";
		
		try {
			
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn.toString());
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		

		
	}
	
	 
}
