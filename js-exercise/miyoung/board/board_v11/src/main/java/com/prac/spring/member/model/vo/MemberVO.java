package com.prac.spring.member.model.vo;

public class MemberVO {
	
	private String userIdx;
	private String userId;
	private String password;
	private int isLeave;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	
	public MemberVO(String userIdx, String userId, String password, int isLeave) {
		super();
		this.userIdx = userIdx;
		this.userId = userId;
		this.password = password;
		this.isLeave = isLeave;
	}

	
	public String getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(String userIdx) {
		this.userIdx = userIdx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(int isLeave) {
		this.isLeave = isLeave;
	}

	
	
	
	@Override
	public String toString() {
		return "Member [userIdx=" + userIdx + ", userId=" + userId + ", password=" + password + ", isLeave=" + isLeave
				+ "]";
	}
	
	
	

	

}
