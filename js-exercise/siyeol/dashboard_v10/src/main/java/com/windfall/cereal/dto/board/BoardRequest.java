package com.windfall.cereal.dto.board;

import java.util.Date;

public class BoardRequest {

	private String seq;
	private String title;
	private String contents;
	private String writer;
	private String lockCheck;

	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getLockCheck() {
		return lockCheck;
	}
	public void setLockCheck(String lockCheck) {
		this.lockCheck = lockCheck;
	}
	
}
