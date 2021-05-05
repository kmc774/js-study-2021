/**
 * 
 */

console.log('ajaxView.js');


	function readView(){
		
		var seq = $('.viewSeq').val();
		var commentReadCount = $("#commentReadCount").val();
		
		$.ajax({
			type : "POST",
			url : "/api/board/loadArticle.do",
			data : {seq : seq},
			//dataType : "json",
			//contentType : 'application/json',
			async: false,
			error : function(){
				alert('통신 실패')			
			},
			success : function(data){
				var tagBuffer = '';
				
				$("#category").text(data["board"].category)
				if(data['board'].lockCheck == '0'){
					$('#title').text(data['board'].title);	
				}else {
					$('#title').text('(비공개)'+data['board'].title);
				}
				$('#writer').text(data['board'].writer);
				$('#modifiedDate').text(data['board'].modifiedDate);
				$('#contents').text(data['board'].contents);
				$('#deleteBtn').attr('onclick','deleteBtn('+data['board'].seq+')');
				$('#moveModifyBtn').attr('onclick','moveModifyBtn('+data['board'].seq+')');
				$('#moveReplyBtn').attr('onclick','moveReplyBtn('+data['board'].seq+')');
				$('#count').text(data['board'].count);
				$('#likeCount').text(data['board'].likeCount + '회');
				if(data["loginMember"] != null){
					$('#loginId').val(data["loginMember"].id)
				}else{
					$("#moveReplyBtn").attr("disabled","disabled");
				}
				$.each(data['file'], function(key, value){
				
					tagBuffer += '<a href="#" onclick=fileDown('+ value.SEQ +')>'+value.ORG_FILE_NAME+'</a>'
					tagBuffer += value.FILE_SIZE+' kb<br>'
				
				});
				$('.blog-file').html(tagBuffer);
			}
		});
	
	}


	//로그인 여부, 작성자와 아이디 일치 체크	
	function writerCheck(){
		
		var seq = $('.viewSeq').val();
		var commentReadCount = $("#commentReadCount").val();
		
		$.ajax({
			type : "POST",
			url : "/api/board/writerCheck.do",
			data : {seq : seq},
			error : function(){
				alert('통신 실패')			
			},
			success : function(data){
				if("null" === data){
					$('#viewModify').css("display", "none");
					$("#likeBtn").attr("disabled", "disabled");
					$(".commentFormTable").css("display", "none")
				}else {
					$('#viewModify').css('display', data);
				}
			}
		});
	}
	
	function commentUpdate(seq){
		
		var commentForm = $(".commentFormContainer #commentForm");
		console.log(commentForm);
		$("#commentList .commentForm").remove();
		commentForm.clone().appendTo('#comment'+seq);
		$("#commentList .commentModifyButton").text("수정");
		$("#commentList .commentModifyButton").attr("onclick","commentModifySave("+seq+")");
		$("#commentList .commentFormTable .commentModify").append("<a href='#;' onclick='cancelCommentModify()'>취소</a>");
	}
	
	function cancelCommentModify(){
		$("#commentList .commentForm").remove();
	}
	
	
	function commentDelete(seq){
		$.ajax({
			type : "POST",
			url : "/api/comment/delete.do",
			data : {seq : seq},		
			error : function(){
				alert("통신 오류");
			},
			success : function(data){
				if(data){
					alert("삭제 완료");
					location.reload();
				} else {
					alert("삭제 실패");
				}			
			}
		});
	}
		
	
	function commentModifySave(seq){
		var boardSeq = $(".viewSeq").val();
		var contents = $("#comment"+seq+" textarea[name=contents]").val();
		
		var formData = new FormData();
		formData.append("seq", seq);
		formData.append("boardSeq", boardSeq);
		formData.append("contents", contents);
		
		$.ajax({
			type : "POST",
			url : "/api/comment/modifySave.do",
			data : formData,
			processData : false,
			contentType : false,
			error : function(){
				alert("통신 오류");
			},
			success : function(data){
				if(data){
					alert("수정 완료");
					location.reload();
				} else {
					alert("수정 실패");
				}			
			}
			
		});
		
	}
	
	
	
	
	function likeClick(){
		var seq = $('.viewSeq').val();
		
		$.ajax({
			type : "POST",
			url : "/api/board/like.do",
			data : {seq : seq},
			error : function(){
				alert('통신 실패')			
			},
			success : function(data){
				if(data == 200){
					alert('추천 완료');
					location.reload();
				}else if(data == 400){
					alert('이미 추천 하셨습니다');
				}else if(data == 401){
					alert('error(콘솔에러 확인)');
				}else{
					alert('error');
				}
				
			}
		});
	}
	
	
	function deleteBtn(seq){
		
		$.ajax({
			type : "POST",
			url : "/api/board/delete.do",
			data : {seq : seq},
			error : function(){
				alert('통신 실패')			
			},
			success : function(data){
				if(data === 200){
					alert("게시글 삭제 완료");
					window.location.href = '/board/list.do';
				}else if(data === 400){
					alert("댓글이 있는 게시글은 삭제 불가능");
				}else if(data === 402){
					alert("답글이 달린 게시글은 삭제 불가능");	
				}else {
					alert("에러 발생, 콘솔 확인")
				}
			}
		});
		
	}
	
	//================== comment ==========================
	
	//=========댓글 읽어오기==========
	function commentRead(){
		
		var seq = $('.viewSeq').val();
		var commentReadCount = parseInt($("#commentReadCount").val())+10;
		$("#commentReadCount").val(commentReadCount);
		
		$.ajax({
			type : "POST",
			url : "/api/comment/list.do",
			data : {
				boardSeq : seq,
				commentReadCount : commentReadCount
			},
			async: false,
			error : function(){
				
			},
			success : function(data){

			var tagBuffer = "";
            var commentCount = data.length;
            
            if(data.length > 0){            
                for(i=0; i<data.length; i++){
                    tagBuffer += "<ol id='comment"+data[i].seq+"' style='list-style:none; list-style-type:none;'>";
					tagBuffer += "<li>"
					tagBuffer += "<p class='commentWriter'><h4><strong>"+data[i].writer+"</strong></h4></p>"
					tagBuffer += "<p class='commentContents'>"+data[i].contents + "</p>";
					
					if(data[i].writer === $("#loginId").val()){
	                    tagBuffer += "<div class='commentModify'>"
						tagBuffer += "<button type='button' class='commentUpdate' onclick='commentUpdate("+data[i].seq+")' value='"+data[i].seq+"'>수정</button>";
						tagBuffer += "&nbsp;&nbsp;"
						tagBuffer += "<button type='button' class='commentDelete' onclick='commentDelete("+data[i].seq+")' value='"+data[i].seq+"'>삭제</button>";
	                    tagBuffer += "</div>";
					}

					tagBuffer += "</li>";
					tagBuffer += "</ol>";
					tagBuffer += "<hr>"
                }            
            } else {                
                tagBuffer += "<div>";
                tagBuffer += "<div><table class='table'><h4><strong>등록된 댓글이 없습니다.</strong></h4>";
                tagBuffer += "</table></div>";
                tagBuffer += "</div>";
            }            
            $("#commentCount").html(commentCount);
            $("#commentList").html(tagBuffer);

			}
			
		});
	}
	
	//댓글 등록
	function saveComment(){
		var boardSeq = $("#viewSeq").val();
		var contents = $("#comment").val();
		
		var formData = new FormData();
		formData.append("boardSeq", boardSeq);
		formData.append("contents", contents);
		
		$.ajax({
			type : "POST",
			url : "/api/comment/save.do",
			data : formData,
			processData: false, 
			contentType: false,
			error : function(){
				alert('통신 실패')
			},
			success : function(data){
				if(data){
					alert("댓글이 등록 됐습니다.");
					location.reload();
				}else {
					alert("댓글 등록에 실패 했습니다.");
				}
			}
			
		})
	}
	
	
	//=================================================
	
	
	
	function fileDown(seq){
		window.location = '/api/fileDown?seq='+seq;
		
	}
	
	function moveReplyBtn(seq){
		
		window.location = "/board/write.do?seq="+seq

	}
	
	function moveModifyBtn(seq){
		window.location.href = '/board/modify.do/'+seq;
	}
	
	
	function goBack(){
		window.history.back();
	}
	
	function goList(){
		window.location.href='/board/list.do';
	}
	
	
	
	

window.onload = function(){
	readView()
	commentRead()
	writerCheck()
}