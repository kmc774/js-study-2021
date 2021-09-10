package com.vue.board.model.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardComment {

    public int comIdx;
    public int typeIdx;
    public String bdComment;
    public Date regDt;
    public Date crtnDt;

}
