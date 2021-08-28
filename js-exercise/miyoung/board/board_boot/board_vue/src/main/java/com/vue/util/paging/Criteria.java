package com.vue.util.paging;


import com.vue.board.model.repository.BoardRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class Criteria {

    // 파라미터를 끌고 다닐 기준들을 클래스로 형성
    public int page;
    public int cntPerPage;
    public String type;
    public String keyword;
    public String orderBy;
    public String orderDir;


}
