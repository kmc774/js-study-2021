<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dashboard write</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="/resources/js/check.js?var=2"></script>
<script src="/resources/js/modify/ajaxModify.js?var=126"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<form id="modifyForm" name="modifyForm">
			<input type="hidden" id="modifySeq" name="seq" value="${seq }">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글쓰기 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<input type="radio" name="category" id="free" value="free" checked="checked"/><span class="ml_10">free</span>&nbsp;&nbsp;&nbsp;&nbsp;
	    						<input type="radio" name="category" id="java" value="java" /><span class="ml_10">java</span>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="category" id="db" value="db" /><span class="ml_10">db</span>&nbsp;
							</td>
						</tr>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" id="title" name="title" value="${board.title }" maxlength="80"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" id="contents" name="contents" maxlength="2048" style="height: 350px;">${board.contents }</textarea></td>
						</tr>
						<tr>
							<td>
								<input type="radio" name="lockCheck" id="unlock" value="0" /><span class="ml_10">공개</span>&nbsp;&nbsp;&nbsp;&nbsp;
    							<input type="radio" name="lockCheck" id="lock" value="1" /><span class="ml_10">비공개</span>&nbsp;
							</td>
						</tr>
						<tr>
							<td>---------------------</td>
						</tr>
						<tr>
							<td>
								<div class="form-group" id="file-list">
							        <a href="#this" onclick="addFile()">파일추가</a><br>
							      
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 글쓰기 버튼 생성 -->
				<input type="button" class="btn btn-primary pull-right" onclick="modifySave()" value="저장">
			</form>
				<button class="btn btn-primary pull-right" onclick="goBack()" value="취소">취소</button>
		</div>
	</div>

</body>
</html>