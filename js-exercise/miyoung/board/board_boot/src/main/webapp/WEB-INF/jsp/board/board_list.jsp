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
    <title>게시판 리스트</title>

</head>
<link rel="stylesheet" href="../../../../resources/css/style.css"/>
<body>

    <h1 id="board" style="cursor: pointer" onclick="location.reload()">게시판 목록</h1>
    <c:if test="${ boardList.size() > 0 }">
        <table class="board_table">
            <tr>
                <th id="num">번호</th>
                <th id="tit">제목</th>
                <th id="autor">작성자</th>
            </tr>
            <c:forEach items="${boardList}" var="board">
                <tr>
                    <td id="resTitle" >${board.bdIdx}</td>
                    <td class="title" id="title" onclick="location.href='/board/view?bdIdx=${board.bdIdx}'">${board.title}</td>
                    <td class="user" id="userId">${board.userId}</td>
                </tr>
            </c:forEach>
        </table>

        <form class="search_section" action="/board/search" method="get">
            <select name="type" class="search_type">
                <option value="all">모두</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="userId">작성자</option>
            </select>
            <input class ="search" type="search" name="keyword">
            <button class="search_btn" type="submit" >검색</button>
            <button class="search_btn" type="button" onclick="location.href='/board/goWrite'">글쓰기</button>
        </form>
        <!-- [s] section pagination -->
        <div class="paging">
            <a href="${context}/${paging.type}/list" class="nav first">《</a>
            <a href="${context}/${paging.type}/list?page=${paging.prev}">〈</a>
            <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
                <a href="${context}/${paging.type}/list?page=${page}"><span>${page}</span></a>
            </c:forEach>
            <a href="${context}/${paging.type}/list?page=${paging.next}">〉</a>
            <a href="${context}/${paging.type}/list?page=${paging.lastPage}">》</a>
        </div>
        <!-- [e] section pagination -->
    </c:if>
    <c:if test="${ boardList.size() == 0 || boardList.size() == null }">
            <h4> 게시판 리스트가 존재하지 않습니다.</h4>
    </c:if>




</body>
</html>
