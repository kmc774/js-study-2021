package com.prac.spring;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })
public class OracleTest {
	
	
	 @Test
	 public void testConnection() throws Exception{
		 
		 try (Connection con = DriverManager
				 .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1*aL201210380")) {
			 
	            System.out.println("\n >>>>>>>>>> Connection Ãâ·Â : " + con + "\n");
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	 }
	
	
	
	

}
