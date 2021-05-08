/**
 * 
 */

	function readModify(){
			
		var seq = $('#modifySeq').val();
		var tagBuffer = '';
		
		
		$.ajax({
			type : 'POST',
			url : '/api/board/loadArticle.do',
			data : {seq : seq},
			dataType : 'json',
			error : function(){
				alert('통신 실패');				
			},
			success : function(data){
				if(data["board"].category === "free"){
					$("#free").attr("checked","checked");
				}else if(data["board"].category === "java"){
					$("#java").attr("checked","checked");
				}else if(data["board"].category === "db"){
					$("#db").attr("checked","checked");
				}
				
				$('#title').val(data['board'].title);
				$('#contents').text(data['board'].contents);
				if(data['board'].lockCheck === '0'){
					$('#unlock').attr('checked','checked');
				}else {
					$('#lock').attr('checked','checked');
				}
				
				$.each(data['file'], function(key, value){
					
					tagBuffer += '<div class="file-group">';
					tagBuffer += '<a href="#">'+value.ORG_FILE_NAME+'</a>'
					tagBuffer += '<input type="hidden" name="fileSeq" value="'+value.SEQ+'">'
					tagBuffer += ' '+value.FILE_SIZE+'kb  '
					tagBuffer += '<a href="#this" name="file-delete">삭제</a>'
					tagBuffer += '</div>'
					
				});
				
				$("#file-list").append(tagBuffer);
				$("a[name='file-delete']").on("click", function(e) {
		            e.preventDefault();
		            deleteFile($(this));
	        	});
				
			}
			
			
		});
		
	}
	
	
	function deleteFile(obj) {
        obj.parent().remove();
		
	}
	
	
	function addFile() {
        var str = "<div class='file-group'><input type='file' name='file'><a href='#this' name='file-delete'>삭제</a></div>";
		
		var fileCnt = $('.file-group').length;
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
	
	
	function modifySave(){
		
		//var modifyForm = $('#modifyForm').serialize();
		var seq = $('#modifySeq').val();
		var category = $("input[name=category]:checked").val();
		var title = $('#title').val();
		var contents = $('#contents').val()
		var lockCheck = $('input[name=lockCheck]:checked').val();
		var files = $('input[name=file]');
		var fileSeqs = $('input[name=fileSeq]');
		
		var formData = new FormData();
		formData.append('seq',seq);
		formData.append('category',category);
		formData.append('title',title);
		formData.append('contents',contents);
		formData.append('lockCheck',lockCheck);
		files.each(function(index, item){
			formData.append('file',item.files[0]);	
		});
		fileSeqs.each(function(index, item){
			formData.append('fileSeq',item.value);	
		});
		
		
		if(blankCk()){
			$.ajax({
				type : 'post',
				url : '/api/board/modifySave.do',
				data : formData,
					processData: false, 
					contentType: false,
				error : function(){
					alert('통신 실패');
				},
				success : function(data){
					if(data){
						window.location.href = '/board/view.do/'+seq;
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
	
	
window.onload = function(){
	readModify();
};