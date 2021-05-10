<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${empty member}">
		<button type="button" id="login_btn" onclick="location.href='/member/loginForm'">로그인</button>
		<button type="button" id="join_btn" onclick="location.href='/member/joinForm'">회원가입</button>
	</c:if> 
	<button type="button" id="board_btn" onclick="location.href='/board/list'">게시판 </button>
	
	
	

</body>
</html>
