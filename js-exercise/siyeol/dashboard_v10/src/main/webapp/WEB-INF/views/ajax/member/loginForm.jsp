<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test_board</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="/resources/js/login/ajaxLogin.js"></script>
</head>
<body>
<div class="container">
<h1>로그인</h1>
	
	<form id="loginForm" name="loginForm">
		<c:if test="${msg == false }">
			<p style="color: red;">로그인 실패! 아이디와 비밀번호 확인해주세요.</p>
		</c:if>
		ID:<input type="text" name="id"><br>
		PW:<input type="password" name="pw"><br>
		<input type="button" onclick="login()" value="login">
	</form>
	</div>
</body>
</html>