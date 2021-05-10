<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8R">
<link rel="stylesheet" href="../../../../resources/css/style.css"/>
<title>게시판 리스트</title>
</head>
<body>


		<h3>게시판 내역</h3>
		
		
	<table class="board_table">
		<tr>
			<th id="num">번호</th>
			<th id="tit">제목</th>
			<th id ="user">작성자</th>
			<th id="cnt">조회수</th>
		</tr>
		<c:forEach items="${boardList}" var="board">
				<tr>
					<td id="resTitle" >${board.bdIdx}</td>
						<c:if test="${board.isPublic eq 0 && member.userId ne board.userId}">
							<td class="title" id="title" style="background-color: Lightgrey;" onclick="view()">비공개</td>
						</c:if>
						<c:if test="${board.isPublic eq 0 && member.userId eq board.userId}">
							<td class="title" id="title" style="background-color: Lightgrey;" onclick="location.href='/board/secretview/${board.bdIdx}'">비공개</td>
						</c:if>
						<c:if test="${board.isPublic eq 1}">
							<td class="title" id="title" onclick="location.href='/board/view/${board.bdIdx}'">${board.title}</td>
						</c:if>
					<td class="user" id="userId">${board.userId}</td>
					<td class="bdCount" id="bdCount">${board.bdCount}</td>
				</tr>
		</c:forEach>
	</table>		
	<br>
	
	<!-- paging section -->
	<div class="paging">
		 <a href="${context}/${paging.type}/list" class="nav first">《</a> <!-- 첫 번째 페이지 가기 -->
         <a href="${context}/${paging.type}/list?page=${paging.prev}">〈</a> <!-- 이전 페이지 가기 -->
         <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page"> 
         	<a href="${context}/${paging.type}/list?page=${page}"><span>${page}</span></a> <!-- 페이징 개수만큼 보이기 -->
         </c:forEach>
         <a href="${context}/${paging.type}/list?page=${paging.next}">〉</a> <!-- 다음 페이지 가기 -->
 	   	 <a href="${context}/${paging.type}/list?page=${paging.lastPage}">》</a> <!-- 마지막 페이지 가기 -->
	</div>
		
				
	
	
	
	
	<div class="view_section">
		<c:if test="${!empty member}">
			<button type="button" class="logout_btn" id="logout_btn" onclick="logout()">로그아웃</button>
		</c:if>
		<c:if test="${empty member}">
			<button type="button" class="logout_btn" id="logout_btn" onclick="location.href='/member/loginForm'">로그인</button>
		</c:if>
		<button type="button" onclick="create()">게시글 등록</button>
	</div>
	
	
	
	

	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
	
	$(function() {
		if(${!empty complete}){
			alert("${complete}");
		}
	}());

	
	function view() {
		alert("비공개 게시글입니다.");
	}
	
	
	
	
	function create(){
		
		location.href="/board/write";
	}

	
	function logout(){
		
		if(confirm("로그아웃 하시겠습니까?") == true){
			location.href='/member/logout';
		}else{
			return;
		}
			
	}

</script>	
	
	
</body>
</html>