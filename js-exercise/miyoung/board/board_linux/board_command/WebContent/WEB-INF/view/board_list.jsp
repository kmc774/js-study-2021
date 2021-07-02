<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.board_table * { border: 1px solid; font-size: 3vh;}
.board_table th{ background-color: lavender; font-size:2vw}
button{ margin-top: 10px;}
td{ text-align : center;}
#tit{width :500px; height :50px}
#num {width :100px; height :50px}
.page_num{font-size: 3vh; cursor:pointer;}
.title{cursor:pointer;}
span {margin : 10px}
</style>
</head>
<body>

	<h1>게시판 목록보기</h1>
	
	<table class ='board_table'>
	<tr>
		<th id="num"> 번호 </th>
		<th id="tit"> 제목 </th>
	</tr>
	<c:forEach var="list" items="${boardList}">
		<tr>
			<td class="seq"> ${list.seq}</td>
			<td class="title" onclick="location.href='/board/detail.do?seq=${list.seq}'">${list.title}</td>
		</tr>
	</c:forEach>
	</table> 
	<br>
	
	<span> [ </span>
	<c:forEach begin="1" end="${paging}">
	<c:set var = "i" value ="${i+1}"/>
		<span class=page_num onclick="location.href= '/board/list.do?page=${i-1}'">${i}</span>
	</c:forEach>
	<span> ] </span>




</body>
</html>
