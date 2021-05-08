package com.windfall.cereal;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.windfall.cereal.dao.BoardDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DataSourceTest {
	
	@Inject
	private DataSource ds;
	
	@Inject
	private BoardDAOImpl dao;
	
	@Test
	public void testConnection() throws Exception{
		try(Connection con = ds.getConnection()) {
			System.out.println("\n >>>>>>>>>>> Connection 출력 : " + con + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
