package com.windfall.cereal.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.windfall.cereal.dao.LikeDAO;
import com.windfall.cereal.dto.LikeVO;

@Service
public class LikeServiceImpl implements LikeService{

	@Inject
	private LikeDAO dao;
	
	@Override
	public int insertLike(LikeVO vo) throws Exception {
		return dao.insertLike(vo);
	}

	@Override
	public LikeVO selectLikeCheck(LikeVO vo) throws Exception {
		return dao.selectLikeCheck(vo);
	}

	@Override
	public int likeCount(long boardSeq) throws Exception {
		return dao.likeCount(boardSeq);
	}

	
	
}
