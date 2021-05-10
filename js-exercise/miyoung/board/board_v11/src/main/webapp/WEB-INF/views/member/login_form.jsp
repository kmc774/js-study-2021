<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h2>로그인</h2>

		<div id="login_form" class="login_form">
			<div class="login_section">
				<label>아이디</label>
				<input type="text" class="userId" id="userId" name="userId">
				<label>비밀번호</label>
				<input type="password" class="password" id="password" name="password">
			</div>
				<button type="submit" class="login_btn" onclick="login()">로그인</button>
		</div>





<script type="text/javascript">
	let login = () =>{
		
		const url = '/member/login';
		let userId = document.getElementById("userId").value;
		let password = document.getElementById("password").value;
		
		let loginObj = new Object(); // 로그인 정보 담을 객체 생성
			loginObj.userId = userId;
			loginObj.password = password;
		
		let headerObj = new Headers(); // 헤더정보 담을 객체 생성
		headerObj.append("content-type","application/json");
		
		// 비동기로 해당 정보 서버에 넘겨주기
		fetch(url,{
			method:"post",
			headers: headerObj,
			body: JSON.stringify(loginObj) //객체전송
			
		}).then(response => {
			if(response.ok == true){ // 상태코드 확인
				return response.text();
			}
			console.log("응답 오류")
		}).then((text) => {
			if(text == 'success'){
				alert("로그인 되었습니다.")
				location.href ='/index';
			}else{
				alert("아이디, 비밀번호를 다시 확인해주세요.");
				location.href ='/member/loginForm';
			}
		}).catch(error =>{
			error.alertMessage();
		})
	
	}

</script>
</body>
</html>