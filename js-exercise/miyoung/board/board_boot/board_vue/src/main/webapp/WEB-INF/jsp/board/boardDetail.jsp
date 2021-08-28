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
        <h1 style="text-align: center">게시글 < ${board.bdIdx} ></h1>
        <label class="b_num">게시글번호 : <input type="text" class="board_bdIdx view_input" id="bdIdx" name ="bdIdx" value="${board.bdIdx}" readonly></label>
        <div class="title_section">
            <label>제목</label>
            <label>조회수 : <input type="text" class="d_bdCount" name ="bdCount" id="d_bdCount" value="" readonly></label>
        </div>
        <input type="text" class="board_title view_input" value="${board.title}" readonly>
        <label>내용</label>
        <textarea rows="20" cols="50" placeholder="내용을 입력하세요." class="board_val" id="veiw_textarea" readonly>${board.content}</textarea>
        <div class="userId_section">
            <c:if test="${!empty files}">
                <span>첨부파일 : </span>
                <c:forEach var="file" items='${files}'>
                    <a class="btn_down-file" onclick="downloadFile(${file.fileIdx})">  [ ${file.originFileName} ]</a>
                </c:forEach>
            </c:if>
        </div>


        <div class="userId_section">
            <label>작성자 : <input type="text" class="d_userId" name ="userId" id="d_userId" value="${board.userId}" readonly>
                <label>등록일 : <input type="text" class="d_regDt" name ="regDt" id="d_regDt" value="${board.regDt}" readonly></label>
                <c:if test ="${!empty board.crtnDt}">
                <label>수정일 : <input type="text" class="d_crtnDt" name ="crtnDt" id="d_crtnDt" value="${board.crtnDt}" readonly></label>
                </c:if>
        </div>
        <label>이 글을 추천하시겠습니까 ?<input type="checkbox" id="recommend" name="recommend" class="recommend"></label>
        <a class="complete" style="display:none">추천완료</a>



        <div class="btn_section">
            <button type="button" class="search_btn"
                    onclick="location.href='/board/go-update?bdIdx=${board.bdIdx}&page=${paging.page}&cntPerPage=${paging.cntPerPage}&type=${paging.type}&keyword=${paging.keyword}&filter=${paging.filter}'">수정하기</button>
            <button type="button" class="search_btn" onclick="location.href='/board/list?page=${paging.page}&cntPerPage=${paging.cntPerPage}&type=${paging.type}&keyword=${paging.keyword}&filter=${paging.filter}'">목록보기</button>
            <button type="button" class="search_btn" onclick="deleteBd()">삭제하기</button>
        </div>
    </div>
</section>


<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
    function deleteBd(){
        if(confirm('게시글을 정말 삭제하시겠습니까 ?')){
            const bdIdx = '${board.bdIdx}';
            const url ='/board/delete';
            $.ajax({
                type:"DELETE",
                url:url,
                contentType:"application/json; charset=UTF-8",
                data:bdIdx,
                success : function(response){
                    if(response == 'success'){
                        alert('게시글이 삭제되었습니다.');
                        location.href='/board/list?page=${paging.page}&cntPerPage=${paging.cntPerPage}&type=${paging.type}&keyword=${paging.keyword}&filter=${paging.filter}';
                    } else if (response == 'fail'){
                        alert('게시글 삭제에 실패했습니다.');
                        location.href='/board/view?bdIdx=${board.bdIdx}&page=${paging.page}&cntPerPage=${paging.cntPerPage}&type=${paging.type}&keyword=${paging.keyword}&filter=${paging.filter}';
                    }
                },
                error:function(){
                    console.log('fail');
                }
            })
        }return;
    }


    function downloadFile(fileIdx){
        location.href = '/board/download?fileIdx=' + fileIdx ;
    }
</script>
</body>
</html>