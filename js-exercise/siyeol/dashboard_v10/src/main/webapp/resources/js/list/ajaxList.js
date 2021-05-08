/**
 * 
 */

console.log('ajaxList.js');


	function moveView(seq){
		
		console.log(seq)
		
		window.location.href = '/board/view.do/'+seq;
		
	};



	function readMore(page){
		//var totalCount = $('.totalCount').val();
		//var contentNum = parseInt(totalCount)+parseInt(number);
		var searchType = $("#searchType option:selected").val();
		var searchValue = $("#searchValue").val();
		var queryString;
		var pageNum = page;
		var tagBuffer = '';
		var pageTag = "";
		
		if(isNaN(pageNum)==true){
			pageNum=1;
		}
		if(searchType != "" && searchValue != ""){
			queryString = "pageNum=" + pageNum + "&searchType=" + searchType + "&searchValue=" + searchValue;
		}else {
			queryString = "pageNum=" + pageNum;
		}
		
		$.ajax({
			type : "GET",
			url : "/api/board/loadList.do?"+queryString,
			error : function(){
				alert('통신 실패')			
			},
			success : function(data){
				/*$('.layout').html(data );
				$('.layout').children('meta,link,title,style,script').remove();
				console.log(data[1].seq);
				*/
				
				//검색 조건 유지
				$("#searchType").val(data["searchType"]).prop("selected",true);
				$("#searchValue").val(data["searchValue"]);
				
				
				$('.totalCount').val(data['boardList'].length);
				$('.boardList').children().remove();
				$.each(data['boardList'], function(key, value){
					tagBuffer += '<tr>';
					tagBuffer += '<td>'+value.seq+'</td>';
					tagBuffer += '<td>'+value.category+'</td>';
					if(value.lockCheck == '0'){
						
						tagBuffer += '<td id="titleCol">';
						
						if(parseInt(value.groupLayer) > 0){
							for(var i = 0; i < parseInt(value.groupLayer); i++){
								tagBuffer += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
							}
							tagBuffer += 'ㄴ'
						}
						
						tagBuffer += '<a href="#;" onclick="moveView('+value.seq+')">'+value.title+'</a>';
						tagBuffer += ' ('+value.commentCount+')</td>';
						
					}else {
						
						if(data['loginMember'] != null){
							
							if(data['loginMember'].id === value.writer){
								
								tagBuffer += '<td id="titleCol">';
								
								if(parseInt(value.groupLayer) > 0){
									for(var i = 0; i < parseInt(value.groupLayer); i++){
										tagBuffer += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
									}
									tagBuffer += 'ㄴ'
								}
								
								tagBuffer += '<a href="#;" onclick="moveView('+value.seq+')">(비공개)'+value.title+'</a>';
								tagBuffer += ' ('+value.commentCount+')</td>';
								
							}else {
								
								tagBuffer += '<td id="titleCol">';
								
								if(parseInt(value.groupLayer) > 0){
									
									for(var i = 0; i < parseInt(value.groupLayer); i++){
										tagBuffer += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
									}
									tagBuffer += 'ㄴ'
									
								}
								
								tagBuffer += '(비공개)</td>';
							}
						}else {
							
								tagBuffer += '<td id="titleCol">';
								
								if(parseInt(value.groupLayer) > 0){
									for(var i = 0; i < parseInt(value.groupLayer); i++){
										tagBuffer += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
									}
									tagBuffer += 'ㄴ'
								}
								
								tagBuffer += '(비공개)</td>';
						}
						
					}				
					tagBuffer += '<td>'+value.writer+'</td>';
					tagBuffer += '<td>'+value.createdDate+'</td>';
					tagBuffer += '<td>'+value.modifiedDate+'</td>';
					tagBuffer += '<td>'+value.count+'</td>';
					tagBuffer += '</tr>';
				});
				$('.boardList').html(tagBuffer);
				
				var pageMaker = data["pageMaker"];
				var prev = parseInt(pageMaker.startPage)-1;
				var next= parseInt(pageMaker.endPage)+1;
				
				if(pageMaker.prev){
					pageTag += "<button onclick='readMore(" + prev+ ")'>&lt;</button>";
				}
				for(var i=pageMaker.startPage; i <= pageMaker.endPage; i++){
					pageTag += "<button onclick='readMore(" + i + ")'>" + i + "</button>";
				}
				if(pageMaker.next){
					pageTag += "<button onclick='readMore(" + next + ")'>&gt;</button>";
				}
				$(".paging").html(pageTag);
					
				
			
			}
		});
	
		
	
	}
	
	
	function logout(){
		$.ajax({
			type : 'get',
			url : '/api/logout.do',
			error : function(){
				alert('통신 실패');
			},
			success : function(data){
			
				window.location.href = '/board/list.do';
			
			}
			
			
		});
	}

window.onload = function(){
	readMore()
}