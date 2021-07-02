<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.board_section label{ display : flex; flex-direction: column; margin-top:10px;}
.board_section input{	border: none; margin-top: 10px; margin_bottom :10px; border-bottom: 1px solid}
 textarea{ resize : none; margin-top : 10px;}
.list_btn{ margin-top: 10px; background-color: salmon; border:1px solid; width: 20vw; height:5vh;cursor:pointer;}
 </style>
</head>
<body>



		
	<div class = 'board_section'> 
		<label>제목</label>
		<input type="text"  value="${boardDetail.title}" readonly>
		<label>내용</label>
		<textarea rows="20" cols="50" placeholder='내용을 입력하세요.' readonly>${boardDetail.content}</textarea>
	</div>
	
	<button class='list_btn' onclick="history.go(-1)"> 목록 </button>

</body>
</html>



