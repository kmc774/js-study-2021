package com.vue.board.controller;


import com.vue.board.model.service.BoardService;
import com.vue.board.model.vo.Board;
import com.vue.board.model.vo.BoardComment;
import com.vue.util.file.FileVo;
import com.vue.util.paging.Criteria;
import com.vue.util.paging.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/board")
public class boardController {

    private BoardService boardService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 생성자 의존성 주입 방식 사용 (thread safe)
    public boardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 전역변수 설정
    private static String[] extArr = { "hwp", "doc", "docx", "ppt", "pptx", "xls", "txt", "csv", "jpg", "jpeg", "gif", "png", "bmp", "pdf" }; // 파일 확장자
    private static int maxSize = 1024 * 1024 * 10; // 10MB 거르기
    private static int maxSumSize = 1024 * 1024 * 100; //100MB 거르기


    /**
     * 리스트 불러오기 + 페이징
     * @param criteria
     * @return
     */
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody Map<String, Object> listBoard ( @ModelAttribute  Criteria criteria   // 파라미터로 가지고 다닐 값들 Criteria 객체로 저장
                                                       ) {
        // 페이징 처리
        Paging paging  = Paging.builder()
                        .criteria(criteria)
                        .total(boardService.selectBoardCnt(criteria.getType() , criteria.getKeyword()))
                        .build();

        // 게시글 리스트 가져오기
        List<Board> boardList = boardService.selectBoardList(paging);
        Map<String, Object> commandMap = new HashMap<>();
        commandMap.put("boardList" , boardList);
        commandMap.put("paging" , paging);

        return commandMap;
    }

    /**
     * 게시글 게시글 보기
     * 주제별 게시글 보기
     * @param
     * @param
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(value = "/view/{bdIdx}" , method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> detailBoard ( @PathVariable("bdIdx") int bdIdx
                                                           ) {
        Board board = boardService.selectBoardDetail(bdIdx);

        List<FileVo> files = boardService.selectAttachedFiles(bdIdx);
        List<BoardComment> comments = boardService.selectComment(bdIdx);

        Map<String, Object> commandMap = new HashMap<>();
        commandMap.put("board" , board);
        commandMap.put("files" , files);
        commandMap.put("comments" , comments);

        return commandMap;
    }

    /**
     * 게시글 등록하기
     * @param board
     * @return
     */
    @RequestMapping(value = "/write" , method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody String WriteBoard (  @RequestParam(required = false) List<MultipartFile> files
                                            , @ModelAttribute Board board) {
        int fileSumSize = 0;
        int result = 0;
        int bdIdx;
        String title  = board.getTitle();
        String content = board.getContent();
        String userId = board.getUserId();
        String userPw = board.getUserPw();
        if(userPw.isEmpty()){
            board.setUserPw(null);
        }



        // 1. Acceptable Extension , Size  --> private method로 분리
        if(files != null) {
            for(int i = 0 ; i < files.size(); i++){
                    // Extension
                String[] strArr = files.get(i).getOriginalFilename().split("\\.");
                for(int j = 0; j < extArr.length; j ++){
                    if(strArr[strArr.length -1] == extArr[j]){  return "extension";  } // response : 등록할 수 없는 파일이 존재합니다.
                }  // Size
                if(files.get(i).getSize() > maxSize){  return "maxSize";  } // response : 파일은 개당 10MB이하만 업로드 가능합니다.
                fileSumSize += files.get(i).getSize(); // 파일의 총 사이즈 계산
            }
        }

        // 2. Acceptable SumMaxSize
        if(fileSumSize  > maxSumSize){  return "SumMaxSize";  }  // response : 총 파일의 크기는 100MB를 넘을 수 없습니다.

        // 3. Check Null
        if (title == null || title.isEmpty()){     return "title"; }
        if (content == null || content.isEmpty()){ return "content";}
        if (userId == null || userId.isEmpty()){   return "userId"; }

        bdIdx = boardService.insertBoard(board);  // 게시글 추가
                boardService.insertFiles(files , bdIdx);  // 파일 추가

        String res = bdIdx > 0 ? "success" : "fail";
        return res;
    }

    /**
     * 게시글 수정하기
     * @return
     */
    @RequestMapping(value = "/update/{bdIdx}" , method = RequestMethod.PUT)
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody String updateBoard(  @PathVariable("bdIdx") int bdIdx
                                            , @RequestParam(required = false) List<MultipartFile> files
                                            , @RequestParam(required = false) List<Integer> delFiles
                                            , @ModelAttribute Board board ){

        int fileSumSize = 0;
        int result = 0;
        String title  = board.getTitle();
        String content = board.getContent();

       /* 없앤 파일 삭제 */
        int delNum = 0;
        if( delFiles != null) {  // 파일 삭제하기
            for (int fileIdx : delFiles) {
                boardService.deleteFile(fileIdx); // 파일 삭제
                delNum++;
            }
            logger.info(delNum + "개의 파일 삭제");
        }

        /* 추가 파일 업로드 */
        // 1. Acceptable Extension , Size  --> private method로 분리
        if(files != null) {
            for(int i = 0 ; i < files.size(); i++){
                // Extension
                String[] strArr = files.get(i).getOriginalFilename().split("\\.");
                for(int j = 0; j < extArr.length; j ++){
                    if(strArr[strArr.length -1] == extArr[j]){  return "extension";  } // response : 등록할 수 없는 파일이 존재합니다.
                }  // Size
                if(files.get(i).getSize() > maxSize){  return "maxSize";  } // response : 파일은 개당 10MB이하만 업로드 가능합니다.
                fileSumSize += files.get(i).getSize(); // 파일의 총 사이즈 계산
            }
        }

        // 2. Acceptable SumMaxSize
        if(fileSumSize  > maxSumSize){  return "SumMaxSize";  }  // response : 총 파일의 크기는 100MB를 넘을 수 없습니다.

        // 3. Check Null
        if (title == null || title.isEmpty()){     return "title"; }
        if (content == null || content.isEmpty()){ return "content";}

        result = boardService.updateBoard(board);  // 게시판 업데이트
        boardService.insertFiles(files , bdIdx);   // 파일 업로드
        logger.info("게시판 업데이트 완료");
        return  result > 0 ? "success" : "fail";
    }

    /**
     * 게시글 삭제하기
     * @param bdIdx
     * @return
     */
    @RequestMapping(value = "/delete/{bdIdx}" , method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody String deleteBoard( @PathVariable("bdIdx") int bdIdx ) {
        int result = boardService.deleteBoard(bdIdx); // 게시글과 파일 동시에 지워주기
        boardService.deleteAttachedFile(bdIdx);
        return result > 0 ? "success" : "fail";  //
    }

    /**
     * 파일 다운로드
     * @param fileIdx
     * @return
     */
    @RequestMapping(value = "/download/{fileIdx}" , method = RequestMethod.GET)
    @CrossOrigin( origins = "http://localhost:8081" , exposedHeaders = {"Content-Disposition"})
    public ResponseEntity downloadFile(@PathVariable("fileIdx") int fileIdx) {

        /* ResponseEntity
         *  HttpEntity를 상속받고, header와 body를 가질 수 있다.
         *  반환값에 상태코드와 응답메시지를 넣어주고 싶을 때 사용
         *  상태코드를 반환할 수 있기 때문에 사용자에게 처리 결과에 대한 응답 코드를 표시해줄 수 있다.
         *
         * Content-Disposition Header :
         *  컨텐츠가 브라우저에 뿌려져야하는 웹페이지 자체인지,
         *  웹페이지 일부인지, attachment로 다운로드 되어야 하는지 알려주는 헤더
         * */

        // 해당 파일이 존재하지 않는 경우
        if(boardService.selectFiles(fileIdx).isEmpty()){
            return new ResponseEntity<>("<h1>다운로드할 파일을 찾을 수 없습니다.</h1>", HttpStatus.NOT_FOUND);
        }

        // 해당 파일 정보 수집
        FileVo file = boardService.selectFiles(fileIdx).get(0);
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition( ContentDisposition
                                        .builder("attachment")
                                        .filename(file.getOriginFileName(), Charset.forName("utf-8")) // Charset 설정 안해주면 'US_ASCII'가 default !
                                        .build());
        // 응답 바디 설정ㅌㅌㅌ
        FileSystemResource resource = new FileSystemResource(file.getFullPath() + file.getRenameFileName());

        return ResponseEntity.ok().headers(headers).body(resource);

    }

    /**
     * 파일 삭제하기
     * @param fileIdx
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(value = "/deleteFile" , method = RequestMethod.DELETE)
    public @ResponseBody String deleteFile (@RequestBody int fileIdx){
        int result = boardService.deleteFile(fileIdx); // typeIdx , bdIdx 구분지어서 메소드 생성 !
        return result > 0 ? "success" : "fail";
    }

    /**
     * 댓글쓰기
     * @param bdIdx
     * @param bdComment
     * @return
     */
    @RequestMapping(value = "/write/comment/{bdIdx}/{bdComment}" , method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody List<BoardComment> insertComment(  @PathVariable("bdIdx") int bdIdx
            , @PathVariable("bdComment") String bdComment ) {

        int result = boardService.addComment(bdIdx , bdComment);
        List<BoardComment> comments = boardService.selectComment(bdIdx);

        return comments;
    }

    /**
     * 댓글수정
     * @param comIdx
     * @return
     */
    @RequestMapping(value = "/update/comment/{bdIdx}/{comIdx}/{bdComment}" , method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody List<BoardComment> updateComment(  @PathVariable("bdIdx") int bdIdx
                                                          , @PathVariable("comIdx") int comIdx
                                                          , @PathVariable("bdComment") String bdComment ) {
        boardService.updateComment(comIdx , bdComment);
        List<BoardComment> comments = boardService.selectComment(bdIdx);
        return comments;
    }

    /**
     * 댓글 삭제하기
     * @param comIdx
     */
    @RequestMapping(value = "/delete/comment/{comIdx}" , method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody void deleteComment( @PathVariable("comIdx") int comIdx ) {
        boardService.deleteComment(comIdx);
    }

    /**
     * 게시글 좋아요 COUNT
     * @param bdIdx
     */
    @RequestMapping(value = "/count/like/{bdIdx}" , method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody int countLike(@PathVariable("bdIdx") int bdIdx){
       int result = boardService.countLike(bdIdx);
       return  result;
    }

    /**
     * 게시글 뷰 COUNT
     * @param bdIdx
     */
    @RequestMapping(value = "/count/view/{bdIdx}" , method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody void countView(@PathVariable("bdIdx") int bdIdx){
        boardService.countView(bdIdx);
    }


}