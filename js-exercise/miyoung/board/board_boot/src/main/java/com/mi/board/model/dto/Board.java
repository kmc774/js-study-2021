package com.mi.board.model.dto;

import lombok.Data;

import java.sql.Date;


@Data
public class Board {

    public String bdIdx;

    public String title;

    public String content;

    public String userId;

    public Date regDt;

    public Date crtnDt;


}
