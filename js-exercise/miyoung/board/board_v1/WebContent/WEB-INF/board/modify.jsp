<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


textarea{
	resize:none;
	margin-top: 10px;
}

input {
	margin-bottom: 10px;
	margin-top: 10px;

}
</style>
<title>Insert title here</title>
</head>
<body>

	<section>
		<div class="board_section">
			<h3>게시글 수정</h3>
			<div class="board_section">
				<form action="/board/modifysave" method="GET" class="board_form" id="board_form" name="board_form" >
					<label class="b_num">게시글번호 : <input type="text" class="board_bdIdx" id="bdIdx" name ="bdIdx" value="${board.bdIdx}" readonly></label>
					<label>제목</label>
					<input type="text" class="board_title" id="title" name ="title" oninput="maxlength(this)" value="${board.title}" required>
					<label>내용</label>
					<textarea rows="20" cols="50"  placeholder="내용을 입력하세요." class="board_val" id="val" name ="val" required>${board.content}</textarea>	
					
					<div class="btn_section"> 
						<button type="button" onclick="modify()">수정</button>
						<button type="button" onclick="back()">취소</button>
					</div>
				</form>
			</div>
		</div>
	</section>



<script type="text/javascript">

	function maxlength(e){
		if(e.value.length > 80){
			alert("제목은 80글자 이내로 입력해주세요.")
			e.value = e.value.slice(0, 80);
			
		}
	}



	function modify(){
	
	var form = document.getElementsByTagName('form')[0];
	
	if(form.checkValidity()){
		
		if(confirm("게시글을 수정하시겠습니까 ?") == true){
			document.getElementById("board_form").submit();
		} else{
			return;
		}

	}else{
		alert("제목/내용은 필수로 입력해야합니다.");
	}
}


 function back(){
	
	if(window.confirm("게시글 수정을 취소하시겠습니까 ?") == true){
		location.href = "/board/detail?bdIdx=" + ${board.bdIdx};
	}else{
		return;
	}
 }



</script>

</body>
</html>