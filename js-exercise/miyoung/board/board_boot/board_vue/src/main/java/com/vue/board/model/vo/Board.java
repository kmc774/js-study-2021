package com.vue.board.model.vo;

import lombok.Data;

import java.sql.Date;


@Data
public class Board {

    public int bdIdx;
    public String title;
    public String content;
    public String userId;
    public String userPw;
    public int likeCount;
    public int viewCount;
    public Date regDt;
    public Date crtnDt;



}
