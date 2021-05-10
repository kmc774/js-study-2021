package com.prac.spring.member.model.service.serviceimpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prac.spring.member.model.repository.MemberRepository;
import com.prac.spring.member.model.service.MemberService;
import com.prac.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	
	private final MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	// 의존성 주입
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	

	@Override
	public int InsertMember(Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		return memberRepository.InsertMember(member);
	}

	
	
	// 아이디 로그인하기
	@Override
	public Member loginMember(Member member) {
		
		Member authMember = memberRepository.loginMember(member.getUserId());

		//encoder된 비밀번호랑 맞지 않거나 해당 유저가 없을 경우 null반환
		if(authMember == null || !encoder.matches(member.getPassword(), authMember.getPassword())) {
			
			return null;
		}
		
		return authMember;
	}



	// 로그아웃 하기
	@Override
	public void logout(HttpSession session) {
		
		session.invalidate();
		
	}



}
