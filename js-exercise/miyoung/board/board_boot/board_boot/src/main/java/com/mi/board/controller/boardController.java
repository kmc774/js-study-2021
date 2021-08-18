package com.mi.board.controller;


import com.mi.board.model.dto.Board;
import com.mi.board.model.service.BoardService;
import com.mi.util.file.FileVo;
import com.mi.util.paging.Criteria;
import com.mi.util.paging.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
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


    /**
     * 리스트 불러오기 + 페이징
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String listBoard (  @ModelAttribute Criteria criteria    // 파라미터로 가지고 다닐 값들 Criteria 객체로 저장
                              , Model model) {

        // 페이징 처리
        Paging paging  = Paging.builder()
                        .criteria(criteria)
                        .total(boardService.selectBoardCnt(criteria.getType() , criteria.getKeyword()))
                        .build();
        // 게시글 리스트 가져오기
        List<Board> boardList = boardService.selectBoardList(paging);

        model.addAttribute("paging" , paging);
        model.addAttribute("boardList" , boardList);
        return "/board/boardList";
    }


    /**
     * 게시글 게시글 보기
     * 주제별 게시글 보기
     * @param
     * @param
     * @param model
     * @return
     */
    @GetMapping("/view")
    public String detailBoard (   @RequestParam int bdIdx
                                , @ModelAttribute Criteria criteria
                                , Model model ) {
        model.addAttribute(boardService.selectBoardDetail(bdIdx));
        List<FileVo> files = boardService.selectFiles(bdIdx);
        model.addAttribute("paging", criteria);
        model.addAttribute("files", files);
        return "/board/boardDetail";
    }


    /**
     * 게시글 작성화면으로 이동하기
     * @return
     */
    @GetMapping("/go-write") // 소문자 + '-' 사용
    public String goWriteBoard () { return "boardWrite"; } // camelCase로 작성


    /**
     * 게시글 등록하기
     * @param board
     * @return
     */
    @PostMapping("/write")
    public @ResponseBody String WriteBoard (  @RequestParam(required = false) List<MultipartFile> files
                                            , @ModelAttribute Board board) {

        String[] extArr = { "hwp", "doc", "docx", "ppt", "pptx", "xls", "txt", "csv", "jpg", "jpeg", "gif", "png", "bmp", "pdf" }; // 파일 확장자
        int fileSumSize = 0;
        int maxSize = 1024 * 1024 * 10; // 10MB 거르기
        int maxSumSize = 1024 * 1024 * 100; //100MB 거르기
        boolean result = false;
        String title  = board.getTitle();
        String content = board.getContent();
        String userId = board.getUserId();

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

        result = boardService.insertBoard(board, files);

        String res = result == true ? "success" : "fail";
        return res;
    }

    /**
     * 게시글 수정페이지 이동
     * @param board
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/go-update")
    public String goModifyBoard (  @ModelAttribute Board board
                                 , @ModelAttribute Criteria criteria
                                 , Model model){

        model.addAttribute(boardService.selectBoardDetail(board.getBdIdx()));
        model.addAttribute("paging" , criteria);
        return "/board/boardModify";
    }

    /**
     * 게시글 수정하기
     * @return
     */
    @PutMapping("/update")
    public @ResponseBody String updateBoard( @RequestBody Map<String, Object> paramMap ){
        int delNum = 0;
        if( paramMap.get("files") != null) {
            List<String> fileList = (List<String>) paramMap.get("files");
            for (String fileIdx : fileList) {
                boardService.deleteFile(fileIdx);
                delNum++;
            }
            logger.info(delNum + "개의 파일 삭제");
        }
        int bdIdx = (int)paramMap.get("bdIdx");
        String title  = (String)paramMap.get("title");
        String content = (String)paramMap.get("content");
        Board board = new Board();
        board.setBdIdx(bdIdx);
        board.setTitle(title);
        board.setContent(content);

        if (title == null   || title.isEmpty()){
            return "title";
        }
        if (content == null || content.isEmpty()){
            return "content";
        }

        int result = boardService.updateBoard(board);
        logger.info("게시판 업데이트 완료");
        return  result > 0 ? "success" : "fail";
    }

    /**
     * 게시글 삭제하기
     * @param bdIdx
     * @return
     */
    @DeleteMapping("/delete/{bdIdx}")
    public @ResponseBody String deleteBoard( @PathVariable("bdIdx") int bdIdx ) {
        int result = boardService.deleteBoard(bdIdx); // 게시글과 파일 동시에 지워주기
        return result > 0 ? "success" : "fail";  //
    }

    /**
     * 파일 다운로드
     * @param fileVo
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity downloadFile(@ModelAttribute FileVo fileVo) {

        /* ResponseEntity
         * HttpEntity를 상속받고, header와 body를 가질 수 있다.
         * 반환값에 상태코드와 응답메시지를 넣어주고 싶을 때 사용
         * 상태코드를 반환할 수 있기 때문에 사용자에게 처리 결과에 대한 응답 코드를 표시해줄 수 있다.
         */

        // 해당 파일이 존재하지 않는 경우
        if(boardService.selectFiles(fileVo.getTypeIdx()).isEmpty()){
            return new ResponseEntity<>("<h1>다운로드할 파일을 찾을 수 없습니다.</h1>", HttpStatus.NOT_FOUND);
        }

        // 해당 파일 정보 수집
        FileVo file = boardService.selectFiles(fileVo.getTypeIdx()).get(0);
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition
                                        .builder("attachment")
                                        .filename(file.getOriginFileName(), Charset.forName("utf-8")) // 인코딩 해줘야 다운로드 파일에 파일 이름이 잘 찍힘
                                        .build());
        // 응답 바디 설정
        FileSystemResource resource = new FileSystemResource(file.getFullPath() + file.getRenameFileName());

        return ResponseEntity.ok().headers(headers).body(resource);

    }


    /**
     * 파일 삭제하기
     * @param fileIdx
     * @return
     */
    @DeleteMapping("/deleteFile")
    public @ResponseBody String deleteFile (@RequestBody String fileIdx){
        int result = boardService.deleteFile(fileIdx);
        return result > 0 ? "success" : "fail";
    }






}