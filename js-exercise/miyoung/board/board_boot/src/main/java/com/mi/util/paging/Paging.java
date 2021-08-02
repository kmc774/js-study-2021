package com.mi.util.paging;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Paging {

    //입력받을 값
    private String type; // 페이징 처리할 항목
    private int currentPage; //현재 페이지
    private int total; //전체 게시물 수
    private int cntPerPage; //페이지당 게시물 수
    private int blockCnt; //한 줄에 표시 될 블록 개수

    //계산해야하는 값
    private int lastPage; //마지막 페이지
    private int blockStart; //시작 블록
    private int blockEnd; //끝 블록
    private int prev; //이전 버튼에 들어갈 값
    private int next; //다음 버튼에 들어갈 값

    //쿼리에서 between문에 지정할 rownum값
    private int queryStart;
    private int queryEnd;

    //검색키워드가 있을 경우
    private String searchType;
    private String keyword;

    private Paging(PagingBuilder builder) {
        this.type = builder.type;
        this.currentPage = builder.currentPage;
        this.total = builder.total;
        this.cntPerPage = builder.cntPerPage;
        this.blockCnt = builder.blockCnt;
        this.searchType = builder.searchType;
        this.keyword = builder.keyword;
        // 최대 페이지 수 이상일 경우 , 가장 마지막 페이지 보여주기
        this.currentPage = this.currentPage > (int)Math.ceil(total/cntPerPage) ? (int)Math.ceil(total/cntPerPage) : builder.currentPage;

        calLastPage();
        calBlockStartAndEnd();
        calQueryStartAndEnd();
        calPrevAndNext();
    }

    private void calLastPage() {
        lastPage = (int)Math.ceil(total/cntPerPage);
    }

    private void calBlockStartAndEnd() {
        //현재 페이지 값보다 작은 blockCnt의 배수 중 가장 큰 값에 1을 더하면 시작값
        blockStart = ((currentPage-1)/blockCnt) * blockCnt + 1;
        blockEnd = blockStart + blockCnt -1;

        blockStart = blockStart < 1? 1:blockStart;
        blockEnd = blockEnd > lastPage? lastPage:blockEnd;
    }

    private void calQueryStartAndEnd() {
        queryEnd = currentPage * cntPerPage;
        queryStart = queryEnd - cntPerPage;
    }

    private void calPrevAndNext() {
        prev = currentPage == 1 ? currentPage : currentPage - 1;
        next = currentPage == lastPage ? lastPage : currentPage + 1;
    }


    public static PagingBuilder builder() {
        return new PagingBuilder();
    }

    public static class PagingBuilder{
        //입력받을 값
        private String type; // 페이징 처리할 항목
        private int currentPage; //현재 페이지
        private int total; //전체 게시물 수
        private int cntPerPage; //페이지당 게시물 수
        private int blockCnt; //한 줄에 표시 될 블록 개수
        private String searchType;
        private String keyword;

        public PagingBuilder type(String val) {
            this.type = val;
            return this;
        }

        public PagingBuilder currentPage(int val) {
            this.currentPage = val;
            return this;
        }

        public PagingBuilder total(int val) {
            this.total = val;
            return this;
        }

        public PagingBuilder cntPerPage(int val) {
            this.cntPerPage = val;
            return this;
        }

        public PagingBuilder blockCnt(int val) {
            this.blockCnt = val;
            return this;
        }

        public PagingBuilder searchType(String val) {
            this.searchType = val;
            return this;
        }

        public PagingBuilder keyword(String val) {
            this.keyword = val;
            return this;
        }

        public Paging build() {
            return new Paging(this);
        }
    }

}
