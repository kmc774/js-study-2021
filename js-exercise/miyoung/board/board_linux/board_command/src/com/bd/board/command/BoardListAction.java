package com.bd.board.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bd.board.model.service.BoardService;
import com.bd.board.model.vo.BoardVO;

public class BoardListAction implements Command {
	
	private BoardService boardService = new BoardService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		String alert = "존재하지 않는 페이지 입니다.";
		String page = request.getParameter("page");
		int pageNum = 0; // pgaeNum 디폴트 값 설정
		if(page != "") { // page 값이 없이 들어왔다면
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		List<BoardVO> boardAllList = new ArrayList<BoardVO>();
		boardList = boardService.boardList(pageNum);
		boardAllList = boardService.boardAllList();
		int paging = boardAllList.size() / 10;
		if(paging < pageNum) {
			// 없는 페이지 선택시
		}
		request.setAttribute("boardList", boardList);
		request.setAttribute("paging", paging);
		request.getRequestDispatcher("/WEB-INF/view/board_list.jsp").forward(request, response);;

	}



}
