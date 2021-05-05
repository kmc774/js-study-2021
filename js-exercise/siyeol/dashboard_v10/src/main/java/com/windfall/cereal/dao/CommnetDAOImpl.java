package com.windfall.cereal.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.windfall.cereal.dto.CommentVO;

@Repository
public class CommnetDAOImpl implements CommentDAO{

	@Inject
	private SqlSession ss;
	
	private final String Namespace = "com.windfall.cereal.mappers.commentMapper";
	
	@Override
	public int insertComment(CommentVO vo) throws Exception {
		return ss.insert(Namespace+".insertComment", vo);
	}

	@Override
	public List<CommentVO> selectCommentList(Map<String, Long> paramMap) throws Exception {
		return ss.selectList(Namespace+".selectCommentList", paramMap);
	}

	@Override
	public int updateComment(CommentVO vo) throws Exception {
		return ss.update(Namespace+".updateComment", vo);
	}

	@Override
	public int deleteComment(long seq) throws Exception {
		return ss.delete(Namespace+".deleteComment", seq);
	}

	@Override
	public int countComment(long boardSeq) throws Exception {
		return ss.selectOne(Namespace+".countComment", boardSeq);
	}

	
	
}
