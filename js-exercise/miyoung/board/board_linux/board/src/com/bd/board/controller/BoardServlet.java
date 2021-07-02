package com.bd.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bd.board.model.dao.BoardDao;
import com.bd.board.model.service.BoardService;
import com.bd.board.model.vo.BoardVO;

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
		
		
		String [] uriArr = request.getRequestURI().split("/");
			switch (uriArr[uriArr.length-1]) {
			case "list.do": listBoard(request,response);
				break;
			case "detail.do": detailBoard(request,response);
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
	

	/**
	 * 게시판의 리스트를 가져오는 메소드
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listBoard(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String page = request.getParameter("page");
		int pageNum = 0; // pgaeNum 디폴트 값 설정
		if(page != "") { // page 값이 없이 들어왔다면
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		List<BoardVO> boardAllList = new ArrayList<BoardVO>();
		boardList = boardService.boardList(pageNum);
		boardAllList = boardService.boardAllList();
		int num = 1;
		if(boardAllList.size() / 10 < pageNum) {
			pw.println(" <script> alert('없는 페이지 입니다.'); location.href ='/board/list.do?page='; </script>" );
		}
		
		
	
		pw.println(" <h3> 게시판 목록보기 </h3>" +
				   " <table class ='board_table'>" +
				   "	<tr>\n" + 
				   "		<th id=\"num\">번호</th>\n" + 
				   "		<th id=\"tit\">제목</th>\n" +
				   "	</tr>" 
				   
					);
		for(int i = 0; i < boardList.size(); i++) { // 해당 페이지의 목록 리스트 출력
		pw.println(
				   "	<tr>"+
				   "		<td class='title' onclick >"+ boardList.get(i).getSeq() + "</td>" +
				   "		<td class='title' style ='cursor:pointer';" +
				   " 		    onclick=\"location.href ='/board/detail.do?seq=" + boardList.get(i).getSeq() +"'\">" + boardList.get(i).getTitle() + "</td>" + 
				   "	</tr>"
				   );
		}
		pw.println(
				   "</table>"
				   );
		
		pw.println("<br><br><div><span class ='page_point'>[ </span>");
					for(int i = 0; i < boardAllList.size()/10; i++) { // 총 게시글의 목록을 뽑아 원하는 갯수만큼 페이징 처리
						pw.print("<span class='page_num' onclick='location.href=\"/board/list.do?page=" + i + "\"'>" + (i + 1) + "</span>");
					} 
		pw.println("<span class ='page_point'> ]</span></div>");
			
		
		
		
		// Style Section
		pw.println("<style> .board_table * { border: 1px solid; font-size: 3vh;}" + 
				   "		.board_table th{ background-color: lavender; font-size:2vw}" + 
				   "		 button{ margin-top: 10px;}" + 
				   "		 td{ text-align : center;}" + 
				   "		 #tit{width :500px; height :50px}" +
				   "		 #num {width :100px; height :50px}" +
				   "		 .page_num{font-size: 3vh; cursor:pointer;}" +	
				   " 		 span {margin : 10px}" +
				   "</style>"
				   );
	
	}
	
	/**
	 * 해당 제목의 게시글을 불러와 출력해내는 메소드
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void detailBoard(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		BoardVO boardDetail = new BoardVO();
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		boardDetail = boardService.boardDetail(seq);
		
		
		// Board Section
		pw.println(
					" <div class = 'board_section'> " +
					"	<label>제목</label>" +
					"	<input type=\"text\"  value=\"" + boardDetail.getTitle() +"\" readonly>" +
					"	<label>내용</label> " +
					"	<textarea rows=\"20\" cols=\"50\" placeholder='내용을 입력하세요.' readonly>" + boardDetail.getContent() + "</textarea> " + 
					" </div> " + 
					" <button class='list_btn' onclick=\"location.href='/board/list.do?page=0'\"> 목록 </button> " 
				   );
		
		
		
		// Style Section
		pw.println(
					"	<style>"
					+ "	 .board_section label{ display : flex; flex-direction: column; margin-top:10px;}"
					+ "	 .board_section input{	border: none; margin-top: 10px; margin_bottom :10px; border-bottom: 1px solid}"
					+ "	  textarea{ resize : none; margin-top : 10px;}	"
					+ "	 .list_btn{ margin-top: 10px; background-color: salmon; border:1px solid; width: 20vw; height:5vh;}"
					+ " </style>"
				
				
					);
		
		
		
		
		
		
	}
	
	
	public void main(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		request.getRequestDispatcher("/index.html").forward(request, response);
		
	}
	
	
	

}
