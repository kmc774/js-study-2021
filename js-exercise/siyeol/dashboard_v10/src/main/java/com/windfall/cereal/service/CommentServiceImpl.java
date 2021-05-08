package com.windfall.cereal.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.windfall.cereal.dao.CommentDAO;
import com.windfall.cereal.dto.CommentVO;

@Service
public class CommentServiceImpl implements CommentService{

	@Inject
	private CommentDAO dao;
	
	@Override
	public int insertComment(CommentVO vo) throws Exception {
		return dao.insertComment(vo);
	}

	@Override
	public List<CommentVO> selectCommentList(Map<String, Long> paramMap) throws Exception {
		return dao.selectCommentList(paramMap);
	}

	@Override
	public int updateComment(CommentVO vo) throws Exception {
		return dao.updateComment(vo);
	}

	@Override
	public int deleteComment(long seq) throws Exception {
		return dao.deleteComment(seq);
	}

	@Override
	public int countComment(long boardSeq) throws Exception {
		return dao.countComment(boardSeq);
	}

	
	
}
