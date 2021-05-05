package com.windfall.cereal.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.windfall.cereal.dto.MemberVO;

@Repository
public class MemberDAOImpl  implements MemberDAO{

	private final String Namespace = "com.windfall.cereal.mappers.memberMapper"; 
	
	@Inject
	private SqlSession ss;
	
	
	@Override
	public int insertMember(MemberVO vo) throws Exception {
		return ss.insert(Namespace+".insertMember", vo);
	}

	@Override
	public MemberVO loginMember(MemberVO vo) throws Exception {
		return ss.selectOne(Namespace+".loginMember", vo);
	}

	@Override
	public MemberVO selectMember(MemberVO vo) throws Exception {
		return ss.selectOne(Namespace+".selectMember", vo);
	}
	
	
	

}
