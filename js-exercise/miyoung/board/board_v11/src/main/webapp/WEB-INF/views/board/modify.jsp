<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../../../resources/css/style.css"/>
<title>Insert title here</title>
</head>
<body>

	<section>
		<div class="board_section">
			<h3>게시글 수정</h3>
			<div class="board_section">
				<form:form modelAttribute="board" action="/board/modifySave" method="post" enctype="multipart/form-data" class="board_form" id="board_form" name="board_form">
					<label class="b_num">게시글번호 : <input type="text" class="board_bdIdx" id="bdIdx" name ="bdIdx" value="${board.bdIdx}" readonly></label>
					<div class="select_public"> 
						<label>공개<input type="radio" id="isPublic_true" name="isPublic" value="1"></label>
						<label>비공개<input type="radio" id="isPublic_false" name="isPublic" value="0"></label>
					</div>
					<label>제목</label><form:errors path="title" cssClass="valid_info"/>
					<input type="text" class="board_title" id="title" name ="title" oninput="maxlength(this)" value="${board.title}" required>
					<label>내용</label><form:errors path="content" cssClass="valid_info"/>
					<textarea rows="20" cols="50"  placeholder="내용을 입력하세요." class="board_val" id="content" name ="content" required>${board.content}</textarea>		
					<div class="userId_section">
						<span>첨부파일 : </span>
						<c:forEach var="file" items="${files}">
				      	 	<a class="file_name">${file.originFileName}</a>
				      	</c:forEach>
				      	<span></span>
					</div>
					<div class="btn_section"> 
						<button type="button" onclick="modify()">수정</button>
						<button type="button" onclick="back()">취소</button>
					</div>
				</form:form>
			</div>
		</div>
	</section>


<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

		
	if(${board.isPublic == 1}){
		$('#isPublic_true').prop('checked', true);
	}else if(${board.isPublic == 0}){
		$('#isPublic_false').prop('checked', true);
	}
		
	
	
	
	function maxlength(e){
		if(e.value.length > 80){
			alert("제목은 80글자 이내로 입력해주세요.")
			e.value = e.value.slice(0, 80);
			
		}
	}



	function modify(){
		var form = document.getElementsByTagName('form')[0];
	      
	      if(form.checkValidity()){
	         if(confirm("게시글을 저장하시겠습니까 ?")){
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
			location.href = "/board/view/" + ${board.bdIdx};
		}else{
			return;
		}
	 }



</script>

</body>
</html>