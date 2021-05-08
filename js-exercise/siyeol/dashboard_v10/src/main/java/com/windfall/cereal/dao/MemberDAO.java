package com.windfall.cereal.dao;

import com.windfall.cereal.dto.MemberVO;

public interface MemberDAO {

	public int insertMember(MemberVO vo) throws Exception;
	public MemberVO loginMember(MemberVO vo) throws Exception;
	public MemberVO selectMember(MemberVO vo) throws Exception;
}
