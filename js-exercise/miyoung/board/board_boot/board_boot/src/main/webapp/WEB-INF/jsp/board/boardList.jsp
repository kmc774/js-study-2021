<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<section class="list_section">
    <h1 id="board" style="cursor: pointer" onclick="location.href='/board/list'">게시판 목록</h1>
    <c:if test="${ boardList.size() > 0 }">
        <table class="board_table">
            <tr>
                <th id="num">번호</th>
                <th id="tit">제목</th>
                <th id="autor">작성자</th>
                <th id="regDt">등록일</th>
                <th id="crtnDt">수정일</th>
            </tr>
            <c:forEach items="${boardList}" var="board">
                <tr>
                    <td id="resTitle">${board.bdIdx}</td>
                    <td class="title" id="title"
                        onclick="location.href='/board/view?bdIdx=${board.bdIdx}'
                                +'&page=${paging.page}&cntPerPage=${paging.cntPerPage}&type=${paging.type}'
                                +'&keyword=${paging.keyword}&filter=${paging.filter}'">${board.title}</td>
                    <td class="user" id="userId">${board.userId}</td>
                    <td>${board.regDt}</td>
                    <c:choose>
                        <c:when test="${board.crtnDt != null}">
                            <td>${board.crtnDt}</td>
                        </c:when>
                        <c:otherwise>
                            <td>-</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>

        <form class="search_section" action="/board/list" method="get">
            <select name="type" class="search_type">
                <option value="all">모두</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="userId">작성자</option>
            </select>
            <input class="search" type="search" name="keyword">
            <input type="hidden" name="cntPerPage" value="${paging.cntPerPage}">
            <input type="hidden" name="filter" value="${paging.filter}">
            <button class="search_btn" id="search_btn" type="button">검색</button>
            <button class="search_btn" type="button" onclick="location.href='/board/go-write'">글쓰기</button>
        </form>
        <div class="dtc_section">
            <div class="check_box_section">
                <a> [ 게시글 수 ] </a>
                <select name="checkNum" id="checkNum" onchange="checkFilterEvent()">
                    <option value="10">10개씩 보기
                    <option value="50">50개씩 보기
                </select>
            </div>
            <div class="filter_section">
                <a> [ 필터 적용 ] </a>
                <select name="filter" id="filter" onchange="checkFilterEvent()">
                    <option value="desc">내림차순
                    <option value="asc">오름차순
                </select>
            </div>
        </div>

        <!-- [s] section pagination -->
        <div class="paging">
            <a href="/board/list?type=${paging.type}&keyword=${paging.keyword}&cntPerPage=${paging.cntPerPage}&filter=${filter}"
               class="nav first">《</a>
            <a href="/board/list?page=${paging.prev}&type=${paging.type}&keyword=${paging.keyword}&cntPerPage=${paging.cntPerPage}&filter=${paging.filter}">〈</a>
            <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
                <a href="/board/list?page=${page}&type=${paging.type}&keyword=${paging.keyword}&cntPerPage=${paging.cntPerPage}&filter=${paging.filter}"><span>${page}</span></a>
            </c:forEach>
            <a href="/board/list?page=${paging.next}&type=${paging.type}&keyword=${paging.keyword}&cntPerPage=${paging.cntPerPage}&filter=${paging.filter}">〉</a>
            <a href="/board/list?page=${paging.lastPage}&type=${paging.type}&keyword=${paging.keyword}&cntPerPage=${paging.cntPerPage}&filter=${paging.filter}">》</a>
        </div>
        <!-- [e] section pagination -->
    </c:if>


    <c:if test="${ boardList.size() == 0 || boardList.size() == null }">
        <h2> 게시판 리스트가 존재하지 않습니다.</h2>
        <button class="search_btn" onclick="location.href='/board/list'" style="margin:auto">목록보기</button>
    </c:if>
</section>


<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
    <%-- option 버튼 미리 선택 --%>
    $(document).ready(function () {
        $('#filter').val('${paging.filter}').prop("selected", true);
        $('#checkNum').val('${paging.cntPerPage}').prop("selected", true);
    })


    // 어떤 옵션을 선택하는지에 따라 조건 설정
    function checkFilterEvent() {
        var filter = $('#filter').val();
        var checkNum = $('#checkNum').val();
        location.href = "/board/list?cntPerPage=" + checkNum + "&filter=" + filter
            + '&page=${paging.page}&type=${paging.type}&keyword=${paging.keyword}';
    }


    var keyword = $('.search').val();
    $('#search_btn').click(function () {
        if ($('.search').val() == '') {
            alert('검색할 내용을 입력해주세요.');
            return;
        }
        $('.search_section').submit();
    })


</script>
</body>
</html>