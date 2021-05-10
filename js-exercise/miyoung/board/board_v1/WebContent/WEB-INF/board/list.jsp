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
		<c:forEach var="boardList" items="${boardList}">
				<tr>
					<td id="resTitle" >${boardList.bdIdx}</td>
					<td class="title" id="title" onclick="location.href='/board/detail?bdIdx=${boardList.bdIdx}'">${boardList.title}</td>
				</tr>
		</c:forEach>
	</table>		

		
				
	
	
	
	<button type="button" onclick="create()">게시글 등록</button>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
<script type="text/javascript">

	function create(){
		
		location.href="/board/write";
	}


</script>	
	
	
</body>
</html>