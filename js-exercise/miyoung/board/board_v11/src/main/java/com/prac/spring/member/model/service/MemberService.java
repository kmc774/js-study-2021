package com.prac.spring.member.model.service;

import javax.servlet.http.HttpSession;

import com.prac.spring.member.model.vo.Member;

public interface MemberService {
	
	
	public int InsertMember(Member member);
	
	public Member loginMember(Member member);
	
	public void logout(HttpSession session);

}
