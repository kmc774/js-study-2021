<%--
  Created by IntelliJ IDEA.
  User: miyoung
  Date: 2021/07/31
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 작성</title>
</head>
<link rel="stylesheet" href="../../../../resources/css/style.css"/>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body>

    <section class="write_section">
        <h2 id="board" >게시글 쓰기</h2>
        <div class="board_form">
            <div>
                <span>제목</span><span class="valid_title" style="display: none">제목을 입력하세요.</span>
                <input type="text" class="w_title" name="title" placeholder="제목을 입력하세요.">
                <span>내용</span><span class="valid_content" style="display: none">내용을 입력하세요.</span>
                <textarea class ="w_content " name="content" placeholder="내용을 입력하세요."></textarea></br>
                <span>작성자</span>
                <input type="text" class="w_userId" name="userId" placeholder="작성자를 입력하세요."><span class="valid_userId" style="display: none">작성자를 입력하세요.</span>
               <div class="btn_section">
                   <button class="search_btn" onclick="addBoard()">등록하기</button>
                   <button class="search_btn" onclick="goList()">목록가기</button>
               </div>
            </div>
        </div>
    </section>




<script>
    function addBoard() {

        if ($('.w_title').val() == '') {
            $('.valid_title').css("display", "inline");
            return;
        }
        if ($('.w_content').val() == '') {
            $('.valid_content').css("display", "inline");
            return;
        }
        if ($('.w_userId').val() == '') {
            $('.valid_userId').css("display", "inline");
            return;
        }

        if (confirm('게시글을 등록하시겠습니까 ?')) {
            var title = $('.w_title').val();
            var content = $('.w_content').val();
            var userId = $('.w_userId').val();

            const url = '/board/writeBoard';
            const paramObj = { 'title' : title, 'content' : content, 'userId' : userId};

            fetch("write", {
                method:"POST",
                headers:{"content-type" : "application/json"},
                body:JSON.stringify(paramObj) //JSON으로 넘기기
            }).then( response => {
                if (response.ok) {
                    return response.text();
                }
            }).then( text => {

                if( text == 'success' ){
                    alert('게시글이 등록되었습니다.');
                    location.href = '/board/list';
                } else if(text == 'ttle'){
                    alert('제목을 입력해주세요.');
                    return;
                } else if(text == 'content'){
                    alert('내용을 입력해주세요.');
                    return;
                } else if(text == 'userId'){
                    alert('작성자를 입력해주세요.');
                    return;
                } else if( text == 'fail'){
                    alert('게시글 등록에 실패했습니다.');
                    return;
                }
            }).catch(error => {
                alert('시스템 오류가 발생했습니다.');
            });
        } return;

    }

    function goList(){
        location.href = '/board/list';
    }
</script>
</body>
</html>
