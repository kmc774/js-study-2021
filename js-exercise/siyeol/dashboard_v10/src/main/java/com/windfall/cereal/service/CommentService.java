package com.windfall.cereal.service;

import java.util.List;
import java.util.Map;

import com.windfall.cereal.dto.CommentVO;

public interface CommentService {

	public int insertComment(CommentVO vo) throws Exception;
	public List<CommentVO> selectCommentList(Map<String, Long> paramMap) throws Exception;
	public int updateComment(CommentVO vo) throws Exception;
	public int deleteComment(long seq) throws Exception;
	public int countComment(long boardSeq) throws Exception;
}
