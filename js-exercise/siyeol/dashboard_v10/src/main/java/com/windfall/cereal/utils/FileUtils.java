package com.windfall.cereal.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.windfall.cereal.controller.ApiController;
import com.windfall.cereal.dto.BoardVO;

@Component("fileUtils")
public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
//	@Qualifier("uploadPath")
//	String uploadPath;
	
	public List<Map<String, Object>> parseFileInfo(BoardVO vo, MultipartFile[] file) throws Exception{
		
		long boardSeq = vo.getSeq();
	
		List<Map<String, Object>> fileList =  new ArrayList<Map<String,Object>>();
		
		File target = new File("C:\\Users\\wdfall\\eclipse-workspace\\windfall\\dashboard_v10\\file_dir");
		if(!target.exists()) target.mkdirs();
		
		for(int i=0; i < file.length; i++) {
			String orgFileName = file[i].getOriginalFilename();
			String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
			String saveFileName = UUID.randomUUID().toString().replace("-", "")+orgFileExtension;
			Long saveFileSize = file[i].getSize();
			
			logger.info("================== file start ==================");
            logger.info("파일 실제 이름: "+orgFileName);
            logger.info("파일 저장 이름: "+saveFileName);
            logger.info("파일 크기: "+saveFileSize);
            logger.info("content type: "+file[i].getContentType());
            logger.info("================== file   END ==================");

            target = new File("C:\\Users\\wdfall\\eclipse-workspace\\windfall\\dashboard_v10\\file_dir", saveFileName);
            file[i].transferTo(target);
            
            Map<String, Object> fileInfo = new HashMap<String, Object>();
            
            fileInfo.put("BOARD_SEQ", boardSeq);
            fileInfo.put("ORG_FILE_NAME", orgFileName);
            fileInfo.put("SAVE_FILE_NAME", saveFileName);
            fileInfo.put("FILE_SIZE", saveFileSize);
            fileList.add(fileInfo);
		}
		return fileList;
	
	}

}
