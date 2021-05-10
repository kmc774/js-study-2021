<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h2>로그인</h2>

		<form action="/member/login" method="POST" id="login_form" class="login_form">
		<div class="login_section">
			<label>아이디</label>
			<input type="text" class="userId" id="userId" name="userId">
			<label>비밀번호</label>
			<input type="password" class="password" id="password" name="password">
		</div>
			<button type="submit" class="login_btn">로그인</button>
		</form>



</body>
</html>