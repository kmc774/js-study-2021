package com.mi.board.model.dto;

import lombok.Data;

import java.sql.Date;


@Data
public class Board {

    public int bdIdx;

    public String title;

    public String content;

    public String userId;

    public Date regDt;

    public Date crtnDt;


}
