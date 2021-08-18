<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miyoung
  Date: 2021/07/28
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 보기</title>
</head>
<link rel="stylesheet" href="../../../../resources/css/style.css"/>
<body>

<section class="board_section">
    <div class="board_form" >
        <h1 style="text-align: center">게시글 수정하기</h1>
        <label class="b_num">게시글번호 : <input type="text" class="board_bdIdx view_input" id="bdIdx" name ="bdIdx" value="${board.bdIdx}" ></label>
        <label>제목</label><span class="valid_title" style="display: none">제목을 입력하세요.</span>
        <input type="text" class="modify_title view_input" value="${board.title}" >
        <label>내용</label><span class="valid_content" style="display: none">내용을 입력하세요.</span>
        <textarea rows="20" cols="50" placeholder="내용을 입력하세요." class="modify_val" id="veiw_textarea" >${board.content}</textarea>
        <!-- <div class="userId_section">
                    <span>첨부파일 : </span>
                    <c:forEach var="file" items="${files}">
                        <a class="btn_down-file"onclick="downloadFile('${file.originFileName}','${file.renameFileName}','${file.savePath}')">${file.originFileName}</a>
                    </c:forEach>
                </div>-->


        <div class="btn_section">
            <button type="button" class="search_btn" onclick="modifyBoard()">수정하기</button>
            <button type="button" class="search_btn"
                    onclick="location.href='/board/view?bdIdx=${board.bdIdx}'
                                          +'&page=${paging.page}&cntPerPage=${paging.cntPerPage}&type=${paging.type}'
                                          +'&keyword=${paging.keyword}&filter=${paging.filter}'">게시글보기</button>
        </div>
    </div>
</section>


<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
    function modifyBoard(){
        if ($('.modify_title').val() == '') { $('.valid_title').css("display", "inline"); }
        if ($('.modify_val').val() == '') { $('.valid_content').css("display", "inline"); }
        if (confirm('게시글을 수정하시겠습니까 ?')) {
            var title = $('.modify_title').val();
            var content = $('.modify_val').val();
            var bdIdx = ${board.bdIdx};
            const url = '/board/update';
            const paramObj = { 'title' : title, 'content' : content, 'bdIdx' : bdIdx};
            fetch( url , {
                method:"PUT",
                headers:{"content-type" : "application/json"},
                body:JSON.stringify(paramObj) //JSON으로 넘기기
            }).then( async response => { // fetch는 프로미스를 반환한다.
                var text = await response.text(); // 프로미스의 result값을 텍스트로 변환
                if (text == 'success') {
                    alert('게시글이 수정되었습니다.');
                     location.href = '/board/view?bdIdx=${board.bdIdx}&page=${paging.page}&cntPerPage=${paging.cntPerPage}'
                                   + '&type=${paging.type}&keyword=${paging.keyword}&filter=${paging.filter}';

                } else if (text == 'ttle') {
                    alert('제목을 입력해주세요.');
                } else if (text == 'content') {
                    alert('내용을 입력해주세요.');
                } else if (text == 'fail') {
                    alert('게시글 수정에 실패했습니다.');
                }
            }).catch(error => {
                alert('시스템 오류가 발생했습니다.');
            });
        } return;
    }
</script>
</body>
</html>