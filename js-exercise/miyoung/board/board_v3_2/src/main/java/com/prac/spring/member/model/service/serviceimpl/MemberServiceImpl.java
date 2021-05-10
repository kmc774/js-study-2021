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
	
	// ������ ����
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	

	@Override
	public int InsertMember(Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		return memberRepository.InsertMember(member);
	}

	
	
	// ���̵� �α����ϱ�
	@Override
	public Member loginMember(Member member) {
		
		Member authMember = memberRepository.loginMember(member.getUserId());

		//encoder�� ��й�ȣ�� ���� �ʰų� �ش� ������ ���� ��� null��ȯ
		if(authMember == null || !encoder.matches(member.getPassword(), authMember.getPassword())) {
			
			return null;
		}
		
		return authMember;
	}



	// �α׾ƿ� �ϱ�
	@Override
	public void logout(HttpSession session) {
		
		session.invalidate();
		
	}



}
