package com.prac.spring.member.model.vo;

public class Member {
	
	private String userIdx;
	private String userId;
	private String password;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	public Member(String userIdx, String userId, String password) {
		super();
		this.userIdx = userIdx;
		this.userId = userId;
		this.password = password;
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





	@Override
	public String toString() {
		return "Member [userIdx=" + userIdx + ", userId=" + userId + ", password=" + password + "]";
	}
	
	
	
	
	
	
	

}
