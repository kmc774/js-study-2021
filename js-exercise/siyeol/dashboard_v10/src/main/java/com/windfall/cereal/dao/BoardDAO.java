package com.windfall.cereal.dao;

import java.util.List;
import java.util.Map;

import com.windfall.cereal.dto.BoardVO;
import com.windfall.cereal.dto.LikeVO;
import com.windfall.cereal.page.PageMaker;

public interface BoardDAO {

	public int selectCount(Map<String, Object> paramMap) throws Exception;
	public List<BoardVO> selectBoard(PageMaker pm) throws Exception;
	public List<BoardVO> selectBoard(Map<String, Object> paramMap) throws Exception;
	public int insertBoard(BoardVO vo) throws Exception; 
	public int deleteBaord(long seq) throws Exception;
	public BoardVO selectBoardOne(long seq) throws Exception;
	public int updateBoard(BoardVO vo) throws Exception;
	public int incCount(long seq) throws Exception;
	public int insertFile(Map<String, Object> map) throws Exception;
	public List<Map<String, Object>> selectFile(long board_seq) throws Exception;
	public Map<String, Object> selectFileInfo(long seq) throws Exception;
	public int deleteFile(long board_seq) throws Exception;
	public int modifyDelete(Map<String, Object> paramMap) throws Exception;
	public int updateGroupOrder(BoardVO vo) throws Exception;
	public int referencedCount(long seq) throws Exception;
}
