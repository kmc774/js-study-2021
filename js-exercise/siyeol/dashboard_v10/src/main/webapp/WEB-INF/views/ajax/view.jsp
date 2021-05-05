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
<script src="/resources/js/view/ajaxView.js?ver=136"></script>
</head>
<body>
	<div class="container">
	<input type="hidden" id="viewSeq" class="viewSeq" value="${seq }">
	<input type="hidden" id="loginId">
	<input type="hidden" id="commentReadCount" value=0>
		<div class="row">
			<div>
				<button id="likeBtn" onclick="likeClick()" class="btn btn-primary">추천하기</button>
				<span id="likeCount">123</span>	
			</div>
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"
							style="background-color: #eeeeee; text-align: center;">글 보기 </th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;"> 카테고리 </td>
						<td id="category"></td>
					</tr>
					<tr>
						<td style="width: 20%;"> 글 제목 </td>
						<td id="title">${board.title }</td>
					</tr>
					<tr>
						<td style="width: 20%;"> 글 제목 </td>
						<td id="writer"></td>
					</tr>
					<tr>
						<td>수정일</td>	
						<td id="modifiedDate"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.modifiedDate }"/></td>
					</tr>
					<tr>
						<td>조회수</td>	
						<td id="count"></td>
					</tr>
					<tr>
						<td>내용</td>	
						<td id="contents" style="min-height: 200px; text-align: left;">${board.contents }</td>
					</tr>
				</tbody>
			</table>
			<hr>
			<span>파일 목록</span>
			<div class="blog-file">
				
			
			</div>
			
			<button id="moveListBtn" onclick="goList()" class="btn btn-primary">목록</button>	
			<span id="viewReply">
				<button id="moveReplyBtn" onclick="moveReplyBtn()" class="btn btn-primary">답글쓰기</button>
			</span>
			<span id="viewModify">
				<button id="moveModifyBtn" onclick="moveModifyBtn()" class="btn btn-primary">수정</button>
				<button id="deleteBtn" onclick="deleteBtn()" class="btn btn-primary">삭제</button>
			</span>
		</div>
	</div>
	
	<div class="commentFormContainer">
	    <form id="commentForm" class="commentForm" name="commentForm" method="post">
	    <br><br>
	        <div>
	            <div>
	                <span><strong>Comments</strong></span> <span id="commentCount"></span>
	            </div>
	            <div>
	                <table class="commentFormTable">                    
	                    <tr>
	                        <td>
	                            <textarea style="width: 1100px" rows="3" cols="30" id="comment" name="contents" placeholder="댓글을 입력하세요"></textarea>
	                            <br>
	                            <div class="commentModify">
	                                <a href='#' onClick="saveComment()" class="commentModifyButton pull-right btn-success">등록</a>
	                            </div>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	        </div>       
	    </form>
	</div>
	
	<div class="commentListContainer">
        <div id="commentList">

        </div>
	</div>
	<div class="row">
		<button class="commentReadMore" onclick="commentRead()">댓글 더 보기</button>
	</div>
	
</body>
</html>