package com.bd.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bd.board.model.service.BoardService;
import com.bd.board.model.vo.BoardVO;

public class BoardDetailAction implements Command{
	
	private BoardService boardService = new BoardService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		BoardVO boardDetail = new BoardVO();
		int seq = Integer.parseInt(request.getParameter("seq"));
		boardDetail = boardService.boardDetail(seq);
		request.setAttribute("boardDetail", boardDetail);
		request.getRequestDispatcher("/WEB-INF/view/board_detail.jsp").forward(request, response);
		
		
		
		
	}

}
