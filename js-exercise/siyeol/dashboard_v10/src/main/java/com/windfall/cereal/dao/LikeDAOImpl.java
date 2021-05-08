package com.windfall.cereal.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.windfall.cereal.dto.LikeVO;

@Repository
public class LikeDAOImpl implements LikeDAO{
	
	@Inject
	private SqlSession ss;

	private final String Namespace = "com.windfall.cereal.mappers.likeBoardMapper";
	
	@Override
	public int insertLike(LikeVO vo) throws Exception {
		return ss.insert(Namespace+".insertLike", vo);
	}

	@Override
	public LikeVO selectLikeCheck(LikeVO vo) throws Exception {
		return ss.selectOne(Namespace+".selectLikeCheck", vo);
	}

	@Override
	public int likeCount(long boardSeq) throws Exception {
		return ss.selectOne(Namespace+".likeCount", boardSeq);
	}

	
}
