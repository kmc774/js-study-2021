package com.prac.spring.common.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	/**
	 * files�� �Ķ���� ������ �޾� utiló�� �� FileVo�� �����ϴ� �޼ҵ�
	 * @param files  @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public List<FileVo> filesUpload(List<MultipartFile> files) throws IllegalStateException, IOException{
		
		List<FileVo> fileList = new ArrayList<FileVo>();
		String savePath = getSavePath(); //savePath ����
		
		// file�� �ִ� ��쿡�� file���� 
		if(files.size() >= 1 && !files.get(0).getOriginalFilename().equals("")) {
					// �ӽ� ����ҿ� ����
			for (MultipartFile multipartFiles : files) {
				
				FileVo fileVo = new FileVo();
				fileVo.setOriginFileName(multipartFiles.getOriginalFilename());
				fileVo.setRenameFileName(UUID.randomUUID().toString()); // ����ũ�� ���� �̸�
				fileVo.setSavePath(savePath);
				fileList.add(fileVo);
				saveFile(fileVo, multipartFiles);
			}
		}
		return fileList;
	}
	
	
	
	
	
	
	/**
	 * ���� �����θ� ��ȯ�ϴ� �޼ҵ�
	 * @return
	 */
	public String getSavePath() {
		Calendar cal = Calendar.getInstance();
		
		return   cal.get(Calendar.YEAR)+"/"
				+ (cal.get(Calendar.MONTH) + 1) +"/"
				+ cal.get(Calendar.DAY_OF_MONTH);
	}

	
	
	/**
	 * ������ �����ϴ� �޼ҵ�(
	 * @param fileVo
	 * @param multipartFile
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private void saveFile(FileVo fileVo, MultipartFile multipartFile) throws IllegalStateException, IOException {
		
		File file = new File(fileVo.getFullPath() + fileVo.getRenameFileName());
	
		if(!file.exists()) { // ����ó��
			new File(fileVo.getFullPath()).mkdirs();
		}
		
		//���ϰ�ü �� ���� �Ű��ֱ�
		multipartFile.transferTo(file);
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
