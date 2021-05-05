package com.windfall.cereal.dto;

public class LikeVO {

	private long seq;
	private long boardSeq;
	private long memberSeq;
	private int likeCheck;
	
	protected LikeVO() {}
	
	public LikeVO(BoardVO board, MemberVO member) {
		this.boardSeq = board.getSeq();
		this.memberSeq = member.getSeq();
		this.likeCheck = 1;
	}
	
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	public long getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(long boardSeq) {
		this.boardSeq = boardSeq;
	}
	public long getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(long memberSeq) {
		this.memberSeq = memberSeq;
	}
	public int getLikeCheck() {
		return likeCheck;
	}
	public void setLikeCheck(int likeCheck) {
		this.likeCheck = likeCheck;
	}
	
	
}
