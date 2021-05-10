<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<div class="board_section">
				<div class="board_form" >
					<label class="b_num">게시글번호 : <input type="text" class="board_bdIdx view_input" id="bdIdx" name ="bdIdx" value="${board.bdIdx}" readonly></label>
					<label>제목</label>
					<input type="text" class="board_title view_input" value="${board.title}" readonly>
					<label>내용</label>
					<textarea rows="20" cols="50" placeholder="내용을 입력하세요." class="board_val" id="veiw_textarea" readonly>${board.content}</textarea>	
					<div class="userId_section">
						<span>첨부파일 : </span>
						<c:forEach var="file" items="${files}">
				      	 	<a class="btn_down-file"onclick="downloadFile('${file.originFileName}','${file.renameFileName}','${file.savePath}')">${file.originFileName}</a>
				      	</c:forEach>
					</div>
					
					
					<div class="userId_section">
						<label>조회수 : </label>
						<input type="text" class="bdCount"  name ="bdCount" id="bdCount" value="${board.bdCount}" readonly>	
						<label>작성자 : </label>
						<input type="text" class="userId"  name ="userId" id="userId" value="${board.userId}" readonly>	
					</div>
					<label>이 글을 추천하시겠습니까 ?<input type="checkbox" id="recommend" name="recommend" class="recommend"></label>
					<a class="complete" style="display:none">추천완료</a>
					
					
					
					<div class="btn_section"> 
						<c:if test="${!empty member && member.userId eq board.userId}">
							<button type="button" onclick="location.href='/board/modify/${board.bdIdx}'">수정하기</button>
							<button type="button" onclick="deleteBd()">삭제하기</button>
						</c:if>
						<button type="button"  onclick="location.href='/board/list'">목록보기</button>
					</div>
				</div>
			</div>
		</div>
	</section>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	
	//같은 아이디로 게시글에 추천했는지 중복확인
	//추천 버튼 누르면 추천 관련 텍스트 뜨게 설정
	$(document).ready(function(){
		$('#recommend').change(function(){
			
			if($('#recommend').is(":checked")){
				
				let data = new Object();
				data.userId = '${member.userId}';
				data.bdUserId = '${board.userId}';
				data.bdIdx = '${board.bdIdx}';
				console.dir(data)
				
				if(data.userId == ''){ //userId 없는 상황 걸러주기
					alert("추천은 로그인 후 가능합니다.");
					$('#recommend').prop('checked', false);
					return;
				}else{
					$.ajax({
						type:"POST",
						url:"/board/recommend",
						data: JSON.stringify(data),
						contentType: "application/json; charset=UTF-8",
						success : function(response){
							console.log(response);
							if(response == 'success'){
								alert('추천되었습니다.');
								document.querySelector(".complete").style.display = "inline";
							}else if(response == 'exit'){
								alert('이미 추천한 게시글입니다.');
								$('#recommend').prop('checked', false);
							}else if(response == 'same'){
								alert('본인이 작성한 게시글은 추천할 수 없습니다.');
								$('#recommend').prop('checked', false);
							}
						},
						error:function(request, status, error){
							console.log("fail");
						}
					})
				}
				
				
			}
		});
	});
	



	$(function() {
		if(${!empty complete}){
			alert("${complete}");
		}
	}());
	
	

	function deleteBd(){
		if(window.confirm("게시글을 삭제하시겠습니까 ?") == true){
			location.href = "/board/delete/${board.bdIdx}";
		}else{
			return;
		}
	}
	
	
	function downloadFile(ofname,rfname,savePath){
		  let params = {'originFileName':ofname,
				  		'renameFileName':rfname,
				  		'savePath':savePath};
		  console.log(params);
	      location.href = "/board/download?originFileName="+ofname+"&&renameFileName="+rfname+"&&savePath="+savePath;
	   }
	

</script>


</body>
</html>