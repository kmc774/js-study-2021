/**
 * 
 */

console.log('ajaxWrite.js');

/*	function val(){
		var form = $('#writeForm').serializeArray();
		console.log($('#writeForm').serializeArray());
		console.log($('#writeForm').serialize());
		
	}*/
	
	

	function writeSave(){
		//var writeForm = $('#writeForm')
		//var formData = new FormData(writeForm);
		var originSeq = $("#originSeq").val();
		var groupOrder = $("#groupOrder").val();
		var groupLayer = $("#groupLayer").val();
		var referencedSeq = $("#referencedSeq").val()
		var category = $("input[name=category]:checked").val();
		var title = $('#title').val();
		var contents = $('#contents').val();
		var lockCheck = $('input[name=lockCheck]:checked').val();
		//var file = $('#file')[0].files[0];
		var files = $('input[name=file]');
		
		var formData = new FormData();
		if(referencedSeq != ""){
			formData.append("referenceSeq", referencedSeq);			
		}
		
		if(originSeq != "" && groupOrder != "" && groupLayer != ""){
			formData.append("originSeq", originSeq);
			formData.append("groupOrder", groupOrder);
			formData.append("groupLayer", groupLayer);	
		}
		
		formData.append("category", category);
		formData.append('title',title);
		formData.append('contents',contents);
		formData.append('lockCheck',lockCheck);
		
		files.each(function(index, item){
			formData.append('file',item.files[0]);	
		});
		

		if(blankCk()){
			$.ajax({
				type : 'post',
				//enctype: 'multipart/form-data',
				url : '/api/board/writeSave.do',
				data : formData,
				processData: false, 
				contentType: false,

				error : function(){
					alert('통신 실패');
				},
				success : function(data){
					if(data){
						window.location.href = '/board/list.do';
					}else {
						alert('저장 실패');
					}
				}
			});	
			
		}
		
	}


	function blankCk() {
		var title = $('#title').val();
		var contents = $('#contents').val()
		
		if (title.replace(/\s | /gi, "").length == 0) {
			alert("제목을 입력하세요");
			$('#title').focus();
			return false
		}
		if (contents.replace(/\s | /gi, "").length == 0) {
			alert("글 내용을 입력하세요");
			$('#contents').focus();
			return false
		}else {
			return true
		}
	}
	
	
	function fileSizeCheck(){
		var files = $(".file");
		var maxSize = 10 * 1024 * 1024;
		let result = false;
		files.each(function(index, item){
			if(item.val){
				if(maxSize < item.files[0].size){
					result = true;
					return false;
				}
			}
		});
		return result;
	}
	
	
	function goBack(){
		window.history.back();
	}
	
	
	
	
	function addFile() {
        var str = "<div class='file-group'><input type='file' name='file'><a href='#this' name='file-delete'>삭제</a></div>";
		
		var fileCnt = $('input[name=file]').length;
		if(fileCnt >= 3){
			alert('파일은 세 개까지만 추가 가능 합니다.')		
		}else{
	        $("#file-list").append(str);
	        $("a[name='file-delete']").on("click", function(e) {
	            e.preventDefault();
	            deleteFile($(this));
	        });
		}
    }
 
    function deleteFile(obj) {
        obj.parent().remove();
    }

	function replyCountCheck(count){

		if(count != ""){
			if(count >= 4){
				alert("이 글은 더 이상 답글을 달 수 없습니다");
				window.history.back();
			}
		}
	}

	
window.onload = function(){

	var count = $("#replyCount").val();
	replyCountCheck(count);

	$("#writeSaveButton").click(function(){
		if(fileSizeCheck()){
			alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다");
		}else {
			writeSave();
		}
	});
}
	
	