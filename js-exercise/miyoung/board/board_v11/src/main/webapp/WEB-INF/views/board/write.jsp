<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../../../resources/css/style.css"/>
<title>게시판 작성</title>
</head>
<body>

	<section>
		<div class="board_section">
			<h2> 게시글을 작성해주세요.</h2>
			<div class="board_section">
				<form:form modelAttribute="board" action="/board/writeSave.do" method="post" enctype="multipart/form-data" class="board_form" id="board_form" name="board_form">
					<div class="select_public"> 
						<label>공개<input type="radio" id="isPublic" name="isPublic" value="1"></label>
						<label>비공개<input type="radio" id="isPublic" name="isPublic" value="0"></label>
					</div>
					<label>제목</label><form:errors path="title" cssClass="valid_info"/>
					<input type="text" class="title"  name ="title" id="title" oninput="maxlength(this)" required="required">
					<label>내용</label><form:errors path="content" cssClass="valid_info"/>
					<textarea rows="20" cols="50" placeholder="내용을 입력하세요." class="content" id="content" name="content" required="required"></textarea>
					<label>첨부파일</label>	
					<input type="file" class="files" name="files" id="files" multiple>
					<div class="userId_section">
						<label>작성자 : </label>
						<input type="text" class="userId"  name ="userId" id="userId" value="${member.userId}" readonly>	
					</div>
					<div class="btn_section"> 
						<button type="button" onclick="save()" id="save_btn">저장</button>
						<button type="button" onclick="back()" id="cancel_btn">취소</button>
					</div>
				</form:form>
			</div>
		</div>
	</section>
	
	
	
	
	
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	
	
	
	function maxlength(e){
		if(e.value.length > 80){
			alert("제목은 80글자 이내로 입력해주세요.")
			e.value = e.value.slice(0, 80);
		}
	}
	
	
   function save(){
	 		var form = document.getElementsByTagName('form')[0];
	      
	 		if(form.checkValidity()){
		         if(confirm("게시글을 저장하시겠습니까 ?") == true){
		            document.getElementById("board_form").submit();
		         } else{
		            return;
		         }
		      }else{
		         alert("제목/내용은 필수로 입력해야합니다.");
		      } 
	   }

	
	function back(){

		if(window.confirm("작성을 취소하시겠습니까 ?") == true){
			location.href='/board/list';
		}else{
			return;
		}
	}
	

	
	
	
</script>
</body>

</html>