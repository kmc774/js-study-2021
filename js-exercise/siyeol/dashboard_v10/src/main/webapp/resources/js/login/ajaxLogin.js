/**
 * 
 */

console.log('ajaxLogin.js');

	function login(){
		var loginForm = $('#loginForm').serialize();
		
		$.ajax({
			type : 'post',
			url : '/api/login.do',
			data : loginForm,
			error : function(){
				alert('통신 실패');
			},
			success : function(data){
				if(data){
					window.location.href = '/board/list.do';
				}else {
					alert('입력하신 정보가 틀립니다');
				}
			}
			
			
		});
	}
	
	
	function join(){
		var joinForm = $('#joinForm').serialize();
		
		$.ajax({
			type : 'post',
			url : '/api/memberSave.do',
			data : joinForm,
			error : function(){
				alert('통신 실패');
			},
			success : function(data){
				if(data){
					window.location.href = '/loginForm';
				}else {
					alert('회원가입 실패');
				}
			}
			
			
		});
	}