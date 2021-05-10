package com.prac.spring.board.model.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Board {
	
	
	private int bdIdx;
	@NotEmpty(message = "[������ �Է����ּ���.]")
	@Size(max = 80, message = "[������ 80���ڸ� �ѱ� �� �����ϴ�.]") //80 �ִ밪 
	private String title;
	@NotEmpty(message = "[���� �Է����ּ���.]")
	private String content;
	private Date applyDate;
	private String updateDate;
	

	public Board() {
		
	}


	public Board(int bdIdx, String title, String content, Date applyDate, String updateDate) {
		super();
		this.bdIdx = bdIdx;
		this.title = title;
		this.content = content;
		this.applyDate = applyDate;
		this.updateDate = updateDate;
	}


	public int getBdIdx() {
		return bdIdx;
	}


	public void setBdIdx(int bdIdx) {
		this.bdIdx = bdIdx;
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


	public Date getApplyDate() {
		return applyDate;
	}


	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}


	public String getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}


	@Override
	public String toString() {
		return "Board [bdIdx=" + bdIdx + ", title=" + title + ", content=" + content + ", applyDate=" + applyDate
				+ ", updateDate=" + updateDate + "]";
	}


	
	

}
