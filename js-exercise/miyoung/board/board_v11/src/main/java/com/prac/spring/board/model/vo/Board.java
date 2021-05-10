package com.prac.spring.board.model.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Board {
	
	
	private int bdIdx;
	private String userId;
	@NotEmpty(message = "[제목을 입력해주세요.]")
	@Size(max = 80, message = "[제목은 80글자를 넘길 수 없습니다.]") //80 최대값 
	private String title;
	@NotEmpty(message = "[내용 입력해주세요.]")
	private String content;
	private Date applyDate;
	private String updateDate;
	private int isPublic;
	private int bdCount;
	

	public Board() {
		
	}


	public Board(int bdIdx, String userId, String title, String content, Date applyDate, String updateDate, int isPublic,
			int bdCount) {
		super();
		this.bdIdx = bdIdx;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.applyDate = applyDate;
		this.updateDate = updateDate;
		this.isPublic = isPublic;
		this.bdCount = bdCount;
	}


	public int getBdIdx() {
		return bdIdx;
	}


	public void setBdIdx(int bdIdx) {
		this.bdIdx = bdIdx;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
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


	public int getIsPublic() {
		return isPublic;
	}


	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}


	public int getBdCount() {
		return bdCount;
	}


	public void setBdCount(int bdCount) {
		this.bdCount = bdCount;
	}


	@Override
	public String toString() {
		return "Board [bdIdx=" + bdIdx + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", applyDate=" + applyDate + ", updateDate=" + updateDate + ", isPublic=" + isPublic + ", bdCount="
				+ bdCount + "]";
	}


	
	
	
	
	

}
