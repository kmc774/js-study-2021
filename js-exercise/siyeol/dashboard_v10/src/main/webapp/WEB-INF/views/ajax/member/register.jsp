<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="/resources/js/login/ajaxLogin.js"></script>
</head>
<body>
	<h1>회원가입</h1>

	<form id="joinForm">
		ID:<input type="text" name="id"><br>
		<font color="red">${message}</font><br>
		PW:<input type="password" name="pw"><br>
		NAME:<input type="text" name="name"><br>
		<input type="button" onclick="join()" value="SIGNUP">
	</form>
</body>
</html>