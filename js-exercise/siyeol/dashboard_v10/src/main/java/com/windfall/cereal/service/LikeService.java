package com.windfall.cereal.service;

import com.windfall.cereal.dto.LikeVO;

public interface LikeService {

	public int insertLike(LikeVO vo) throws Exception;
	public LikeVO selectLikeCheck(LikeVO vo) throws Exception;
	public int likeCount(long boardSeq) throws Exception;
}
