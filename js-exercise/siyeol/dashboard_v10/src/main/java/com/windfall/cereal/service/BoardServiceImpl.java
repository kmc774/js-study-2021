package com.windfall.cereal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.windfall.cereal.dao.BoardDAO;
import com.windfall.cereal.dto.BoardVO;
import com.windfall.cereal.dto.board.BoardResponse;
import com.windfall.cereal.page.PageMaker;
import com.windfall.cereal.utils.FileUtils;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO dao;
	
	@Resource(name = "fileUtils")
	FileUtils fileUtils;
	
	public PageMaker getPageMaker(int pageNum, int contentnum, Map<String, Object> paramMap) throws Exception{
		return PageMaker.createPageMaker(pageNum, contentnum, dao.selectCount(paramMap));
	}
	
	@Override
	public List<BoardVO> selectBoard(int pageNum, int contentNum, Map<String, Object> paramMap) throws Exception {
		PageMaker pm = PageMaker.createPageMaker(pageNum, contentNum, dao.selectCount(paramMap));
		pm.setPageNum(pm.getPageNum()*10);
		return dao.selectBoard(pm);
	}

	@Override
	public List<BoardVO> selectBoard(Map<String, Object> paramMap) throws Exception {
		int pageNum = (Integer.parseInt(String.valueOf(paramMap.get("pageNum")))-1)*10;
		int contentNum = Integer.parseInt(String.valueOf(paramMap.get("contentNum"))); 
		paramMap.put("pageNum", pageNum);
		paramMap.put("contentNum", contentNum);
		return dao.selectBoard(paramMap);
	}

	@Override
	public int insertBoard(BoardVO vo, MultipartFile[] file) throws Exception {
		
//		if(referencedSeq != null) {
//			dao.updateReferencedSeq(referencedSeq);
//		}
		
		dao.updateGroupOrder(vo);
		int result = dao.insertBoard(vo);
		
		List<Map<String,Object>> fileList = fileUtils.parseFileInfo(vo, file);
		for(int i=0; i<fileList.size(); i++) {
			dao.insertFile(fileList.get(i));
		}
		return result;		
		
	}

	@Override
	public int deleteBoard(long seq) throws Exception {
		
		int referencedCount = dao.referencedCount(seq);
		if(referencedCount > 0) {
			return -1;
		}else {
			return dao.deleteBaord(seq);
		}
	}

	@Override
	public BoardVO selectBoardOne(long seq) throws Exception {

		return dao.selectBoardOne(seq);
	}

	@Override
	public int updateBoard(BoardVO vo, MultipartFile[] file) throws Exception {
		int result = dao.updateBoard(vo);
		List<Map<String,Object>> fileList = fileUtils.parseFileInfo(vo, file);
		for(int i=0; i<fileList.size(); i++) {
			dao.insertFile(fileList.get(i));
		}
		return result;
	}

	@Override
	public int incCount(long seq) throws Exception {
		return dao.incCount(seq);
	}

	@Override
	public List<Map<String, Object>> selectFile(long seq) throws Exception {
		return dao.selectFile(seq);
		
	}

	@Override
	public Map<String, Object> selectFileInfo(long seq) throws Exception {
		return dao.selectFileInfo(seq);
	}

	@Override
	public int deleteFile(long board_seq) throws Exception {
		return dao.deleteFile(board_seq);
	}

	@Override
	public int modifyDelete(Map<String, Object> paramMap) throws Exception {
		return dao.modifyDelete(paramMap);
	}
	
	
	
}
