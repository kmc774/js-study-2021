package com.prac.spring.common.file;

import java.sql.Date;

public class FileVo {
	
	
	private int fileIdx;
	private String typeIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private String fullPath;
	private Date regDate;
	
	String path = "C:\\prac\\spring_upgrade\\resources\\upload\\"; // 파일이 저장될 디폴트 폴더
	
	public FileVo() {
		// TODO Auto-generated constructor stub
	}
	
	


	public FileVo(int fileIdx, String typeIdx, String originFileName, String renameFileName, String savePath,
			String fullPath, Date regDate, String path) {
		super();
		this.fileIdx = fileIdx;
		this.typeIdx = typeIdx;
		this.originFileName = originFileName;
		this.renameFileName = renameFileName;
		this.savePath = savePath;
		this.fullPath = fullPath;
		this.regDate = regDate;
		this.path = path;
	}
	
	
	
	public int getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}
	public String getTypeIdx() {
		return typeIdx;
	}
	public void setTypeIdx(String typeIdx) {
		this.typeIdx = typeIdx;
	}
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public String getRenameFileName() {
		return renameFileName;
	}
	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	public String getFullPath() {
		return  path + savePath;
	}

	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "FileVo [fileIdx=" + fileIdx + ", typeIdx=" + typeIdx + ", originFileName=" + originFileName
				+ ", renameFileName=" + renameFileName + ", savePath=" + savePath + ", fullPath=" + fullPath
				+ ", regDate=" + regDate + ", path=" + path + "]";
	}
	
	
	
	

}
