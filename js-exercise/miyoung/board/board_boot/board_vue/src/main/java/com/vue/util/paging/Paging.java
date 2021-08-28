package com.vue.util.paging;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Paging {

    //입력받을 값
    private int total;

    //계산해야하는 값
    private int page; //현재 페이지
    private int cntPerPage; //페이지당 게시물 수
    private int blockCnt; //한 줄에 표시 될 블록 개수
    private int lastPage; //마지막 페이지
    private int blockStart; //시작 블록
    private int blockEnd; //끝 블록
    private int prev; //이전 버튼에 들어갈 값
    private int next; //다음 버튼에 들어갈 값

    //쿼리에서 between문에 지정할 rownum값
    private int queryStart;
    private int queryEnd;

    //검색키워드가 있을 경우
    private String type;
    private String keyword;
    private String orderBy;
    private String orderDir;


    private Paging(PagingBuilder builder) {
        this.total = builder.total;
        this.page = builder.criteria.page == 0 ? 1 : builder.criteria.page;
        this.cntPerPage = builder.criteria.cntPerPage;
        this.blockCnt = 5;
        this.type = builder.criteria.type;
        this.keyword = builder.criteria.keyword;
        this.orderBy = builder.criteria.orderBy;
        this.orderDir = builder.criteria.orderDir == null ? "desc" : builder.criteria.orderDir;
        // 최대 페이지 수 이상일 경우 , 가장 마지막 페이지 보여주기
        this.page = this.page > (int)Math.ceil((double)total/cntPerPage) ? (int)Math.ceil((double)total/cntPerPage): this.page;

        calLastPage();
        calBlockStartAndEnd();
        calQueryStartAndEnd();
        calPrevAndNext();
    }


    private void calLastPage() {
        lastPage = (int)Math.ceil((double)total/cntPerPage);
    }

    private void calBlockStartAndEnd() {
        blockStart = ((page-1)/blockCnt) * blockCnt + 1;
        blockEnd = blockStart + blockCnt -1;

        blockStart = blockStart < 1? 1:blockStart;
        blockEnd = blockEnd > lastPage ? lastPage : blockEnd;
    }

    private void calQueryStartAndEnd() {
        queryEnd = page * cntPerPage;
        queryStart = queryEnd - cntPerPage < 1 ? 0 : queryEnd - cntPerPage;
    }

    private void calPrevAndNext() {
        prev = page == 1 ? page : page - 1;
        next = page == lastPage ? lastPage : page + 1;
    }


    public static PagingBuilder builder() {
        return new PagingBuilder();
    }

    public static class PagingBuilder{
        //입력받을 값
        public Criteria criteria;
        public int total;

        public PagingBuilder criteria(Criteria criteria) {
            this.criteria = criteria;
            return this;
        }
        public PagingBuilder total(int val) {
            this.total = val;
            return this;
        }

        public Paging build() {
            return new Paging(this);
        }
    }



}