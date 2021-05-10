package com.prac.spring.board.model.service.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prac.spring.board.model.repository.BoardRepository;
import com.prac.spring.board.model.service.BoardService;
import com.prac.spring.board.model.vo.Board;
import com.prac.spring.common.file.FileUtil;
import com.prac.spring.common.file.FileVo;
import com.prac.spring.common.paging.Paging;


@Service
public class BoardServiceImpl implements BoardService{
	
	
	private final BoardRepository boardRepository;
	
	// ������ ������ ����
	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	
	// ��ü ����Ʈ ��ȸ
	@Override
	public Map<String, Object> viewList(int currentPage) {
		
		Paging paging = Paging.builder()
							  .currentPage(currentPage)
							  .blockCnt(5)
							  .cntPerPage(10)
							  .type("board")
							  .total(boardRepository.selectContentCnt())
							  .build();
				
		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("paging", paging);
		boardMap.put("boardList", boardRepository.selectBoardList(paging));
		
		
		return boardMap;
	}



	
	// �Խñ� ���� �ҷ�����
	@Override
	public Map<String, Object> viewDetail(int bdIdx) {
		
		Board board = boardRepository.viewDetail(bdIdx);
		List<FileVo> files = boardRepository.viewFile("b" + bdIdx);
		
		Map<String , Object> commandMap = new HashMap<String, Object>();
		commandMap.put("board", board);
		commandMap.put("files", files);
	
		return commandMap;
	}

	
	
	
	@Override
	public int updateCount(int bdIdx) {
		return boardRepository.updateCount(bdIdx);
	}


	
	
	
	// �Խñ� ����ϱ�
	@Override
	public int insertBoard(Board board, List<MultipartFile> files) {
		
		FileUtil fileUtil = new FileUtil();
		boardRepository.insertBoard(board); //�Խ��� ����
		
		try {
			List<FileVo> fileList = fileUtil.filesUpload(files);
			for (FileVo fileVo : fileList) {
				boardRepository.insertFile(fileVo); //��ȯ�� ����Ʈ  insert
			}
			return '1';
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return '0';
		}
	}
	
	
	// �Խñ� �����ϱ�
	@Override
	public int updateBoard(Board board) {

		return boardRepository.updateBoard(board);
	}
	
	
	
	
	// �Խñ� �����ϱ�
	@Override
	public int deleteBoard(int bdIdx) {
		
		return boardRepository.deleteBoard(bdIdx);
	}


	
	
	@Override
	public String checkRecommend(int bdIdx, String userId) {
		
		String res = null;
		
		if(boardRepository.checkRecommend(bdIdx, userId) > 0) { //������ ��õ�� ������ �ִٸ�,
			res = "exit";
		}else if(boardRepository.checkRecommend(bdIdx, userId) < 1){ //��õ�� ������ ���ٸ�,
			int result = boardRepository.insertRecommend(bdIdx, userId);
			res = result > 0 ? "success" : "fail";
		}
		return res;
	}


	
		
}
