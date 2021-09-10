package com.mi.util.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    /**
     * files를 파라미터 값으로 받아 util처리 후 FileVo에 매핑하는 메소드
     *
     * @param files @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public static List<FileVo> filesUpload(List<MultipartFile> files, int bdIdx) throws IllegalStateException, IOException {

        List<FileVo> fileList = new ArrayList<FileVo>();
        String savePath = getSavePath(); //savePath 설정

        // file이 있는 경우에만 file저장
        if (files.size() >= 1 && !files.get(0).getOriginalFilename().equals("")) {
            // 임시 저장소에 보관
            for (MultipartFile multipartFiles : files) {

                FileVo fileVo = new FileVo();
                fileVo.setOriginFileName(multipartFiles.getOriginalFilename());
                fileVo.setRenameFileName(UUID.randomUUID().toString()); // 유니크한 파일 이름 새로짓기
                fileVo.setSavePath(savePath);
                fileVo.setTypeIdx(bdIdx);
                fileList.add(fileVo);
                saveFile(fileVo, multipartFiles);
            }
        }
        return fileList;
    }

    /**
     * 파일 저장경로를 반환하는 메소드
     *
     * @return
     */
    public static String getSavePath() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.YEAR) + "/"
                + (cal.get(Calendar.MONTH) + 1) + "/"
                + cal.get(Calendar.DAY_OF_MONTH) + "/";
    }

    /**
     * 파일을 저장하는 메소드(
     *
     * @param fileVo
     * @param multipartFile
     * @throws IllegalStateException
     * @throws IOException
     */
    private static void saveFile(FileVo fileVo, MultipartFile multipartFile) throws IllegalStateException, IOException {

        File file = new File(fileVo.getFullPath() + fileVo.getRenameFileName());

        if (!file.exists()) { // 예외처리 -- 폴더가 존재하지 않을 경우엔 새로 만들어주기
            new File(fileVo.getFullPath()).mkdirs();
        }

        //파일객체 한 번에 옮겨주기
        multipartFile.transferTo(file);


    }
}
