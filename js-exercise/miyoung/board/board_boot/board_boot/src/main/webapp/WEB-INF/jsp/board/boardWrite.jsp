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
    <title>게시글 작성</title>
</head>
<link rel="stylesheet" href="../../../../resources/css/style.css"/>
<body>

<section class="write_section">
    <h1 id="board" >게시글 쓰기</h1>
    <form class="board_form" >
        <div>
            <span>제목</span><span class="valid_title" style="display: none">제목을 입력하세요.</span>
            <input type="text" class="w_title" name="title" placeholder="제목을 입력하세요.">
            <span>내용</span><span class="valid_content" style="display: none">내용을 입력하세요.</span>
            <textarea class ="w_content " name="content" placeholder="내용을 입력하세요."></textarea>
            <div class="etc_section">
                <span>작성자</span>
                <input type="text" class="w_userId" name="userId" placeholder="작성자를 입력하세요."><span class="valid_userId" style="display: none">작성자를 입력하세요.</span>
                <span>첨부파일</span>
                <input type="file" class="w_files" name="files" id="files" accept="jpg" multiple>
                <div id="fileChange">
                </div>
            </div>
            <div class="btn_section">
                <button class="search_btn" type="button" onclick="addBoard()">등록하기</button></br>
                <button class="search_btn" type="button" onclick="goList()">목록가기</button>
            </div>
        </div>
    </form>
</section>



<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>

    $(document).ready(function(){
        $('.w_files').on("change", fileCheck); // change
    });


    var fileNum;
    var fileSumSize = 0;
    var filesArr = new Array();
    const fileExtArr = ['hwp', 'doc', 'docx', 'ppt', 'pptx', 'xls', 'txt', 'csv', 'jpg', 'jpeg', 'gif', 'png', 'bmp', 'pdf'];
    const maxSize = 1024 * 1024 * 10; // 10MB 거르기
    const maxSumSize = 1024 * 1024 * 100; // 100MB 거르기

    function fileCheck(e){
        $("#fileChange").empty(); // 다시 클릭할 경우 초기화
        fileSumSize = 0;
        let files = e.target.files;
        filesArr = Array.prototype.slice.call(files); // 얕은 복사 후 입력된 파일 나눠서 배열에 저장

        for(let fileNum = 0 ; fileNum < filesArr.length; fileNum++){
            var extension = filesArr[fileNum].name.split('.').pop().toLowerCase();
            if( $.inArray(extension , fileExtArr) == -1 ){  // extension 값이 fileExtArr에 없을 경우 -1을 반환함
                alert('등록할 수 없는 형태의 파일이 존재합니다.');
                $('.w_files').val(""); // 초기화
                return;
               }
            if ( filesArr[fileNum].size > maxSize ) {
                alert('파일은 개당 10MB 이하만 업로드 가능합니다.');
                $('.w_files').val(""); // 초기화
                return;
               }
                $('#fileChange').append('<a id="' + filesArr[fileNum].name +'">'+ filesArr[fileNum].name+ '</a></br>');
                fileSumSize += filesArr[fileNum].size;  // 파일들의 총 사이즈 구하기
          }
        if(fileSumSize > maxSumSize){
            alert('총 파일의 크기는 100MB를 넘을 수 없습니다.');
            $('.w_files').val("");
            $('#fileChange').empty();
            return;
        }
     }


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
            var form = $('.board_form')[0];
            var formData = new FormData(form);
            const url = '/board/write';
            $.ajax({
                type:"POST",
                url:url,
                enctype:"multipart/form-data",
                processData: false,
                contentType: false,
                data: formData,
                success : function(response) {
                    if (response == 'success') {
                        alert('게시글이 등록되었습니다.');
                        location.href = '/board/list';
                    } else if (response == 'extension') {
                        alert('등록할 수 없는 형태의 파일이 존재합니다.');
                    } else if (response == 'maxSize') {
                        alert('파일은 개당 10MB 이하만 업로드 가능합니다.');
                    } else if (response == 'SumMaxSize') {
                        alert('총 파일의 크기는 100MB를 넘을 수 없습니다.');
                    } else if (response == 'ttle') {
                        alert('제목을 입력해주세요.');
                    } else if (response == 'content') {
                        alert('내용을 입력해주세요.');
                    } else if (response == 'userId') {
                        alert('작성자를 입력해주세요.');
                    } else if (response == 'fail') {
                        alert('게시글 등록에 실패했습니다.');
                    }
                }, error : function (){
                    console.log('fail');
                }
            })
        } ;
    }
    function goList(){
        location.href = '/board/list';
    }
</script>
</body>
</html>