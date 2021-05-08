package com.windfall.cereal.dto.board;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.windfall.cereal.dto.BoardVO;
import com.windfall.cereal.dto.MemberVO;

public class BoardResponse {
	
	private String seq;
	private String title;
	private String contents;
	private String writer;
	private String createdDate;
	private String modifiedDate;
	private String lockCheck;
	private String count;
	private String likeCount;
	private String category;
	private String commentCount;
	private String originSeq;
	private String groupOrder;
	private String groupLayer;
	 
	
	
	protected BoardResponse() {
	}


	public BoardResponse(BoardVO vo) {
		this.seq = String.valueOf(vo.getSeq());
		this.title = vo.getTitle();
		this.contents = vo.getContents();
		this.writer = vo.getWriter();
		this.createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getCreatedDate());
		this.modifiedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getModifiedDate());
		this.lockCheck = String.valueOf(vo.getLockCheck());
		this.count = String.valueOf(vo.getCount());
		this.category = vo.getCategory();
		this.originSeq = String.valueOf(vo.getOriginSeq());
		this.groupOrder = String.valueOf(vo.getGroupOrder());
		this.groupLayer = String.valueOf(vo.getGroupLayer());
	}
	
	
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getLockCheck() {
		return lockCheck;
	}
	public void setLockCheck(String lockCheck) {
		this.lockCheck = lockCheck;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


	public String getOriginSeq() {
		return originSeq;
	}


	public void setOriginSeq(String originSeq) {
		this.originSeq = originSeq;
	}


	public String getGroupOrder() {
		return groupOrder;
	}


	public void setGroupOrder(String groupOrder) {
		this.groupOrder = groupOrder;
	}


	public String getGroupLayer() {
		return groupLayer;
	}


	public void setGroupLayer(String groupLayer) {
		this.groupLayer = groupLayer;
	}

	
	
}
