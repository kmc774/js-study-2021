package com.bd.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bd.board.command.BoardDetailAction;
import com.bd.board.command.BoardListAction;
import com.bd.board.model.service.BoardService;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board/*")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	BoardService boardService = new BoardService();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//	BoardActCommand boardCommand = new BoardActCommand();
		
		String [] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
						// 요청을 
		case "list.do": BoardListAction boardListAction = new BoardListAction();
						boardListAction.execute(request, response);
			break;
		case "detail.do": BoardDetailAction boardDetailAction = new BoardDetailAction();
						  boardDetailAction.execute(request, response);
			break;
		default : main(request, response);
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	public void main(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		request.getRequestDispatcher("/index.html").forward(request, response);
		
	}
	
	
	

}
