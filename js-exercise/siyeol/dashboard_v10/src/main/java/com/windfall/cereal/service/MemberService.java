package com.windfall.cereal.service;

import com.windfall.cereal.dto.MemberVO;

public interface MemberService {

	public int insertMember(MemberVO vo) throws Exception;
	public MemberVO loginMember(MemberVO vo) throws Exception;
	public MemberVO selectMember(MemberVO vo) throws Exception;
}
