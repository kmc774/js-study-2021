package com.bd.board.model.vo;

import java.util.Date;

public class BoardVO {
	
	private int seq;
	private String title;
	private String content;
	private Date regDate;
	
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}


	public BoardVO(int seq, String title, String content, Date regDate) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", content=" + content + ", regDate=" + regDate + "]";
	}
	
	
	
	
	

}
