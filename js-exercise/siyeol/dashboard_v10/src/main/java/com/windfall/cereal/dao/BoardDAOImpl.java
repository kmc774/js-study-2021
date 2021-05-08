package com.windfall.cereal.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.windfall.cereal.dto.BoardVO;
import com.windfall.cereal.dto.LikeVO;
import com.windfall.cereal.page.PageMaker;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Inject
	private SqlSession ss;
	
	private final String Namespace = "com.windfall.cereal.mappers.boardMapper";
	
	
	
	@Override
	public int selectCount(Map<String, Object> paramMap) throws Exception {
		return ss.selectOne(Namespace+".selectCount", paramMap);
	}

	@Override
	public List<BoardVO> selectBoard(PageMaker pm) throws Exception {
		return ss.selectList(Namespace+".selectBoard", pm);
	}

	@Override
	public List<BoardVO> selectBoard(Map<String, Object> paramMap) throws Exception {
		return ss.selectList(Namespace+".selectBoard", paramMap);
	}

	@Override
	public int insertBoard(BoardVO vo) throws Exception{
		return	ss.insert(Namespace+".insertBoard", vo);
	}

	@Override
	public int deleteBaord(long seq) throws Exception {
		return ss.delete(Namespace+".deleteBoard", seq);
	}

	@Override
	public BoardVO selectBoardOne(long seq) throws Exception {
		return ss.selectOne(Namespace+".selectBoardOne", seq);
	}

	@Override
	public int updateBoard(BoardVO vo) throws Exception {
		return ss.update(Namespace+".updateBoard", vo);
	}

	@Override
	public int incCount(long seq) throws Exception {
		return ss.update(Namespace+".incCount",seq);
	}


	@Override
	public int insertFile(Map<String, Object> map) throws Exception {
		return ss.insert(Namespace+".insertFile", map);
	}

	@Override
	public List<Map<String, Object>> selectFile(long board_seq) throws Exception {
		return ss.selectList(Namespace+".selectFile", board_seq);
	}

	@Override
	public Map<String, Object> selectFileInfo(long seq) throws Exception {
		return ss.selectOne(Namespace+".selectFileInfo", seq);
	}

	@Override
	public int deleteFile(long board_seq) throws Exception {
		return ss.delete(Namespace+".deleteFile", board_seq);
	}

	@Override
	public int modifyDelete(Map<String, Object> paramMap) throws Exception {
		return ss.delete(Namespace+".modifyDelete", paramMap);
	}

	@Override
	public int updateGroupOrder(BoardVO vo) throws Exception {
		return ss.update(Namespace+".updateGroupOrder", vo);
	}

	@Override
	public int referencedCount(long seq) throws Exception {
		return ss.selectOne(Namespace+".referencedCount", seq);
	}
	

}
