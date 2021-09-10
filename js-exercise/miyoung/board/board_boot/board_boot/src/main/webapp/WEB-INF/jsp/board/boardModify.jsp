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
    <div class="board_form">
        <h1 style="text-align: center">게시글 수정하기</h1>
        <label class="b_num">게시글번호 : <input type="text" class="board_bdIdx view_input" id="bdIdx" name="bdIdx"
                                            value="${board.bdIdx}"></label>
        <label>제목</label><span class="valid_title" style="display: none">제목을 입력하세요.</span>
        <input type="text" class="modify_title view_input" value="${board.title}">
        <label>내용</label><span class="valid_content" style="display: none">내용을 입력하세요.</span>
        <textarea rows="20" cols="50" placeholder="내용을 입력하세요." class="modify_val"
                  id="veiw_textarea">${board.content}</textarea>
        <div class="userId_section">
            <span>첨부파일 : </span>
            <c:forEach var="file" items="${files}">
                <a class="btn_del-file" name="${file.fileIdx} " id="${file.fileIdx}"
                   onclick="deleteFile('${file.fileIdx}')">${file.originFileName}</a>

            </c:forEach>
        </div>


        <div class="btn_section">
            <button type="button" class="search_btn" onclick="modifyBoard()">수정하기</button>
            <button type="button" class="search_btn"
                    onclick="location.href='/board/view?bdIdx=${board.bdIdx}'
                            +'&page=${paging.page}&cntPerPage=${paging.cntPerPage}&type=${paging.type}'
                            +'&keyword=${paging.keyword}&filter=${paging.filter}'">게시글보기
            </button>
        </div>
    </div>
</section>


<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>

    var fileArr = new Array(); // 전역 변수 추가

    function modifyBoard() {

        if ($('.modify_title').val() == '') {
            $('.valid_title').css("display", "inline");
        }
        if ($('.modify_val').val() == '') {
            $('.valid_content').css("display", "inline");
        }

        if (confirm('게시글을 수정하시겠습니까 ?')) {
            var title = $('.modify_title').val();
            var content = $('.modify_val').val();
            var bdIdx = ${board.bdIdx};
            var files = fileArr
            const url = '/board/update';  // -- 원하는 첨부파일 시퀀스를 쿼리스트링으로 보내기
            const paramObj = {
                'title': title
                , 'content': content
                , 'bdIdx': bdIdx
                , 'files': files
            }; // 오브젝트 객체로 보내기
            $.ajax({
                type: "PUT",
                url: url,
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify(paramObj),
                success: function (response) {
                    if (response == 'success') {
                        alert('게시글이 수정되었습니다.');
                        location.href = '/board/view?bdIdx=${board.bdIdx}&page=${paging.page}&cntPerPage=${paging.cntPerPage}'
                            + '&type=${paging.type}&keyword=${paging.keyword}&filter=${paging.filter}';
                    } else if (response == 'ttle') {
                        alert('제목을 입력해주세요.');
                    } else if (response == 'content') {
                        alert('내용을 입력해주세요.');
                    } else if (response == 'fail') {
                        alert('게시글 수정에 실패했습니다.');
                    }
                }, error: function () {
                    console.log('fail');
                }
            });
        }
        location.reload();
    }

    function deleteFile(fileIdx) {
        if (confirm('해당 파일을 삭제하시겠습니까 ?')) {
            $('#' + fileIdx).remove();
            fileArr.push(fileIdx); // 없앨 fileList에 추가
        }
        return;
    }

</script>
</body>
</html>