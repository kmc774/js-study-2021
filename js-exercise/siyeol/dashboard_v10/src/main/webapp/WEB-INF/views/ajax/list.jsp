<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<title>test_dashboard</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="/resources/js/check.js"></script>
<script src="/resources/js/list/ajaxList.js?var=132"></script>
<style type="text/css">
 #titleCol {
 	width: 50%;
 	text-align: left;
 }

</style>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${session.id == null }">
				<a href="/loginForm">로그인</a>
				<a href="/register">회원가입</a>
			</c:when>
			<c:otherwise>
				${session.id }
				<a href="#;" onclick="logout()">로그아웃</a>
			</c:otherwise>
		</c:choose>
		<input type="hidden" id="totalCount" class="totalCount" name="totalCount" value="10" >
		<div class = "row">
		<div>
			<a href = "/board/write.do" class="btn btn-primary pull-right">글쓰기</a>
		</div>	
		<div style="padding-right: 10px">
			<select name="searchType" id="searchType">
				<option value="" selected="selected">---</option>
				<option value="title">제목</option>
				<option value="writer">작성자</option>
				<option value="contents">내용</option>
				<option value="category">카테고리</option>
			</select>
			<div style="padding-right: 10px">
				<input type="text" name="searchValue" id="searchValue">
				<button id="searchButton" onclick="readMore()">검색</button>
			</div>
		</div>
		
			<table class="table table-striped" style="text-align:center; border:1px solid #dddddd"> 
				<thead>
					<tr>
					<input type="hidden" id="lockCheck" class="lockCheck" name="lockCheck"  >
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">카테고리</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						<th style="background-color: #eeeeee; text-align: center;">수정일</th>
						<th style="background-color: #eeeeee; text-align: center;">조회수</th>
					</tr>
				</thead>

			<tbody id="boardList" class="boardList">
				
				
			</tbody>
			</table>
			
		</div>
		<!-- 페이징 -->
		<div class="paging">
			
			<%-- <c:if test="${pm.prev }">
				<a name href="/board/list?pageNum=${pm.startPage - 1 }">&lt;</a>
			</c:if>
			
			<c:forEach begin="${pm.startPage }" end="${pm.endPage }" var="idx">
				<a href="/board/list?pageNum=${idx }">${idx }</a>
			</c:forEach>
			
			<c:if test="${pm.next }">
				<a href="/board/list?pageNum=${pm.endPage + 1 }">&gt;</a>
			</c:if> --%>
		</div>
	</div>
</body>
</html>