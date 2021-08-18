package com.mi.util.paging;


import com.mi.board.model.repository.BoardRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class Criteria {

    public int page;
    public int cntPerPage;
    public String type;
    public String keyword;
    public String filter;

}
