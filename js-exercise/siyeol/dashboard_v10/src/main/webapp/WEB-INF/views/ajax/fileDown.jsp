<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax download test</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="/resources/js/check.js?var=1"></script>
<script src="/resources/js/write/ajaxWrite.js?ver=128"></script>
<script src="/resources/js/fileDown.js?ver=128"></script>
</head>
<body>
	<div class="container">
		<button onclick="ajaxDownload()">ajax 엑셀 다운로드</button>
		<br/>
		<br/>
		<form action="/formDownload" method="POST">
				<button type="submit">form 엑셀 다운로드</button>	
		</form>
	
	</div>

</body>
</html>