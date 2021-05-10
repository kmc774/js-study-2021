<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8R">
<style type="text/css">

.title {
	cursor: pointer;
	
}

#tit{
	width: 200px;
	heigt: 50px;
	text-align: center;
}

#num{
	width:100px;
	heigt: 50px;
	text-align: center;
}

.board_table *{
	border: 1px solid;
	font-size: 1vw;
	
}

.board_table th{
	background-color: lavender;
}

 button{
	margin-top: 10px;
}

 td{
	text-align : center;
	
}

.logout_btn{
	margin-bottom : 20px;
	background-color: salmon;
	border: 2px solid;
	border-radius: 5px;
	
}

button{
 cursor: pointer;
}

.paging{
		font-size: 1.3vw;
		width:30vw;
		left:25vw;
		justify-content:space-around;
	}

</style>
<title>게시판 리스트</title>
</head>
<body>

	
	<h3>게시판 내역</h3>
	
	
	<table class="board_table">
		<tr>
			<th id="num">번호</th>
			<th id="tit">제목</th>
		</tr>
		<c:forEach items="${boardList}" var="board">
				<tr>
					<td id="resTitle" >${board.bdIdx}</td>
					<td class="title" id="title" onclick="location.href='/board/view/${board.bdIdx}'">${board.title}</td>
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
		
				
	
	
	
	<br>
	<c:if test="${!empty member}">
		<button type="button" class="logout_btn" id="logout_btn" onclick="logout()">로그아웃</button>
	</c:if>
	<br>
	<button type="button" onclick="create()">게시글 등록</button>
	
	
	
	
	
	
	
	
	
	
	
	
	
<script type="text/javascript">

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