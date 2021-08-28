package com.vue.util.file;

import lombok.Data;

import java.sql.Date;

@Data
public class FileVo {

    private int fileIdx;
    private int typeIdx;
    private String originFileName;
    private String renameFileName;
    private String savePath;
    private String fullPath;
    private Date regDt;

    String path = "/Users/miyoung/Desktop/wdfall_study/study/board_boot/board_vue/resources/upload/";


    public String getFullPath(){
        return path + savePath;
    }


}
