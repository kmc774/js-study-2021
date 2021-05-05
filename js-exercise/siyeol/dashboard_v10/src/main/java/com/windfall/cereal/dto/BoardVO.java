package com.windfall.cereal.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class BoardVO {

	private long seq;
	private String title;
	private String contents;
	private String writer;
	private Date createdDate;
	private Date modifiedDate;
	private int lockCheck;
	private long count;
	private String category;
	private int originSeq = 0;
	private int groupOrder = 0;
	private int groupLayer = 0;
	private long referenceSeq;
	
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public int getLockCheck() {
		return lockCheck;
	}
	public void setLockCheck(int lockCheck) {
		this.lockCheck = lockCheck;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getOriginSeq() {
		return originSeq;
	}
	public void setOriginSeq(int originSeq) {
		this.originSeq = originSeq;
	}
	public int getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(int groupOrder) {
		this.groupOrder = groupOrder;
	}
	public int getGroupLayer() {
		return groupLayer;
	}
	public void setGroupLayer(int groupLayer) {
		this.groupLayer = groupLayer;
	}
	public long getReferenceSeq() {
		return referenceSeq;
	}
	public void setReferenceSeq(long referenceSeq) {
		this.referenceSeq = referenceSeq;
	}

	
	
}
