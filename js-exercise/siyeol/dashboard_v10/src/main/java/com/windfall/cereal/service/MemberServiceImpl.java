package com.windfall.cereal.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.windfall.cereal.dao.MemberDAO;
import com.windfall.cereal.dto.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO dao;
	
	@Override
	public int insertMember(MemberVO vo) throws Exception {
		return dao.insertMember(vo);
	}

	@Override
	public MemberVO loginMember(MemberVO vo) throws Exception {
		return dao.loginMember(vo);
	}

	@Override
	public MemberVO selectMember(MemberVO vo) throws Exception {
		return dao.selectMember(vo);
	}

	
}
