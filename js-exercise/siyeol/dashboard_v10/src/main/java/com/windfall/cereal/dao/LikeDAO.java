package com.windfall.cereal.dao;

import com.windfall.cereal.dto.LikeVO;

public interface LikeDAO {

	public int insertLike(LikeVO vo) throws Exception;
	public LikeVO selectLikeCheck(LikeVO vo) throws Exception;
	public int likeCount(long boardSeq) throws Exception;
}
