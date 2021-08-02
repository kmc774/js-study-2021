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

<section>

        <div class="board_section">
            <div class="board_form" >
                <label class="b_num">게시글번호 : <input type="text" class="board_bdIdx view_input" id="bdIdx" name ="bdIdx" value="${board.bdIdx}" readonly></label>
                <label>제목</label>
                <input type="text" class="board_title view_input" value="${board.title}" readonly>
                <label>내용</label>
                <textarea rows="20" cols="50" placeholder="내용을 입력하세요." class="board_val" id="veiw_textarea" readonly>${board.content}</textarea>
               <!-- <div class="userId_section">
                    <span>첨부파일 : </span>
                    <c:forEach var="file" items="${files}">
                        <a class="btn_down-file"onclick="downloadFile('${file.originFileName}','${file.renameFileName}','${file.savePath}')">${file.originFileName}</a>
                    </c:forEach>
                </div>-->


                <div class="userId_section">
                    <label>조회수 : </label>
                   <%-- <input type="text" class="bdCount"  name ="bdCount" id="bdCount" value="${board.bdCount}" readonly> --%>
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
                    <button type="button"  onclick="history.back()">목록보기</button>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="http://code.jquery.com/jquery-latest.js"></script>

</body>
</html>
