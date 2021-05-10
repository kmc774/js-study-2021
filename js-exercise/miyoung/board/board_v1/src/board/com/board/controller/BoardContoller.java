package board.com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.com.board.model.service.BoardService;
import board.com.board.model.vo.Board;

@WebServlet("/board/*")
public class BoardContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	BoardService boardService = new BoardService();
	

	
    public BoardContoller() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String[] uriArray = request.getRequestURI().split("/");
		
		switch (uriArray[uriArray.length-1]) {
		
			case "list" : viewList(request, response);
				break;
			case "detail" : viewDetail(request, response);
				break;
			case "write" : writeBoard(request, response);
				break;
			case "writesave" : saveBoard(request, response);
				break;
			case "modify" : viewModify(request, response);
				break;
			case "modifysave" : modifySave(request, response);
				break;
			case "delete" : deleteBoard(request, response);
			
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	
	
	
	// 게시글 리스트 보기
	private void viewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Board> boardList = boardService.boardList();
		
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(request, response);
		
	}
	
	
	
	
	// 상세보기
	private void viewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
	
		Board board = boardService.veiwDetail(bdIdx);
		
		
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/board/view.jsp").forward(request, response);
	}
	
	
	
	
	
	// 게시글 작성페이지 이동
	private void writeBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/board/write.jsp").forward(request, response);
		
	}
	
	
	
	
	// 게시글 업로드
	private void saveBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("val");

		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		

		int result = boardService.insertBoard(board);
		
		if(result > 0) {
			
			request.setAttribute("alertMsg", "게시글이 등록되었습니다.");
			request.setAttribute("url", "/board/list");
			request.getRequestDispatcher("/WEB-INF/common/result.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("alertMsg", "게시글 등록이 실패했습니다.");
			request.setAttribute("url", "/board/list");
			request.getRequestDispatcher("/WEB-INF/common/result.jsp").forward(request, response);
		}
		
	}
	
	
	// 게시글 수정페이지 이동
	private void viewModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		Board board = boardService.veiwDetail(bdIdx);
		
		request.setAttribute("board", board); //board 정보 넘겨주기
		request.getRequestDispatcher("/WEB-INF/board/modify.jsp").forward(request, response);
		
	}
	
	
	
	
	
	// 게시글 수정
	private void modifySave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		String title = request.getParameter("title");
		String content = request.getParameter("val");
		
		Board board = new Board();
		
		board.setBdIdx(bdIdx);
		board.setTitle(title);
		board.setContent(content);
		
		
		result = boardService.modifyBoard(board);
		
		if(result > 0) {
			request.setAttribute("alertMsg", "게시글이 수정되었습니다.");
			request.setAttribute("url", "/board/detail?bdIdx="+bdIdx);
			request.getRequestDispatcher("/WEB-INF/common/result.jsp").forward(request, response);
			
		}else {
			request.setAttribute("alertMsg", "게시글 수정이 실패했습니다.");
			request.setAttribute("url", "/board/detail?bdIdx="+bdIdx);
			request.getRequestDispatcher("/WEB-INF/common/result.jsp").forward(request, response);
		}
	}
	
	
	
	
	// 게시글 삭제하기
	private void deleteBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
	
		result = boardService.deleteBoard(bdIdx);
		
		if(result > 0) {
			request.setAttribute("alertMsg", "게시글이 삭제되었습니다.");
			request.setAttribute("url", "/board/list");
			request.getRequestDispatcher("/WEB-INF/common/result.jsp").forward(request, response);
		}else {
			request.setAttribute("alertMsg", "게시글이 삭제되지 않았습니다.");
			request.setAttribute("url", "/board/list");
			request.getRequestDispatcher("/WEB-INF/common/result.jsp").forward(request, response);
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
