package com.prac.spring.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.prac.spring.member.model.repository.MemberRepository;
import com.prac.spring.member.model.vo.Member;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MemberRepoTest {
	
	
	@Autowired
	MemberRepository memberRpository;
	
	
	
	
	@Test
	public void selectMemberById() {
		String userId = "test";
		//String password = "12345";
		Member member = memberRpository.loginMember(userId);
		System.out.println(member);
	}
	
	
	
	

}
