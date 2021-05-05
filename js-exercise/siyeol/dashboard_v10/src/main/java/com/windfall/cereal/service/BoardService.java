package com.windfall.cereal.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.windfall.cereal.dto.BoardVO;
import com.windfall.cereal.page.PageMaker;

public interface BoardService {

	public List<BoardVO> selectBoard(int PaeNum, int contentNum,  Map<String, Object> paramMap) throws Exception;
	public List<BoardVO> selectBoard(Map<String, Object> paramMap) throws Exception;
	public int insertBoard(BoardVO vo, MultipartFile[] file) throws Exception;
	public int deleteBoard(long seq) throws Exception;
	public BoardVO selectBoardOne(long seq) throws Exception;
	public int updateBoard(BoardVO vo, MultipartFile[] file) throws Exception; 
	public PageMaker getPageMaker(int pageNum, int contentNum, Map<String, Object> paramMap) throws Exception;
	public int incCount(long seq) throws Exception;
	public List<Map<String, Object>> selectFile(long seq) throws Exception;
	public Map<String,Object> selectFileInfo(long seq) throws Exception;
	public int deleteFile(long board_seq) throws Exception;
	public int modifyDelete(Map<String, Object> paramMap) throws Exception;
}
