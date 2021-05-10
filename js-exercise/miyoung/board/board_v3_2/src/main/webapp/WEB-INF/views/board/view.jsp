<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">

.board_form {
    display: flex;
    flex-direction: column;
    width: 50vw;
}

.btn_section{
	margin-top: 10px;
	
}

.board_bdIdx{
	border: none;
	margin-bottom : 10px;
}

.b_num{
	display: none;
}

input {
	border: none;
	margin-bottom: 10px;
	margin-top: 10px;

}

textarea{
	resize:none;
	border: none;
	margin-top: 10px;
}

.board_title{
	background-color: lavender;
}

.board_val{
	background-color: lavender;
}
</style>
<title>Insert title here</title>
</head>
<body>

	<section>
		<div class="board_section">
			<div class="board_section">
				<div class="board_form" >
					<label class="b_num">게시글번호 : <input type="text" class="board_bdIdx" id="bdIdx" name ="bdIdx" value="${board.bdIdx}" readonly></label>
					<label>제목</label>
					<input type="text" class="board_title" value="${board.title}" readonly>
					
					<label>내용</label>
					<textarea rows="20" cols="50" placeholder="내용을 입력하세요." class="board_val" readonly>${board.content}</textarea>	
					
					<div class="btn_section"> 
						<button type="button" onclick="location.href='/board/modify/${board.bdIdx}'">수정하기</button>
						<button type="button" onclick="deleteBd()">삭제하기</button>
						<button type="button" onclick="location.href='/board/list'">목록보기</button>
					</div>
				</div>
			</div>
		</div>
	</section>

<script type="text/javascript">

	function deleteBd(){
		if(window.confirm("게시글을 삭제하시겠습니까 ?") == true){
			location.href = "/board/delete/${board.bdIdx}";
		}else{
			return;
		}
	}

</script>


</body>
</html>