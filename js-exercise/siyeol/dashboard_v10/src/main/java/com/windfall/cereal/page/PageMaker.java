package com.windfall.cereal.page;


public class PageMaker {

	private int totalCount;	// 전체 데이터 갯수
	private int pageNum;	//현재 페이지 번호
	private int contentNum;	//한 페이지에 표시할 데이터 갯수
	private int startPage=1;	//현재페이지 블록의 시작 페이지
	private int endPage=5;	//현재 페이지 블록의 마지막 페이지
	private boolean prev;
	private boolean next;
	private int currentblock;	//현재 페이지 블록
	private int lastblock;	//마지막 페이지 블록
	
	protected PageMaker() {
	}
	
	// 생성자
	public static PageMaker createPageMaker(int pageNum, int contentNum, int totalCount) {
		PageMaker pm = new PageMaker();
		pm.setTotalCount(totalCount);
		pm.setPageNum(pageNum-1);
		pm.setContentNum(contentNum);
		pm.setCurrentblock(pageNum);
		pm.setLastblock(pm.getTotalCount());
		pm.prevOrNext(pageNum);
		pm.setStartPage(pm.getCurrentblock());
		pm.setEndPage(pm.getLastblock(), pm.getCurrentblock());
		
		return pm;
	}
	
	public void prevOrNext(int pageNum) {	//이전, 다음 페이지 블록 설정
		if(calcpage(totalCount, contentNum)<6) {
			setPrev(false);
			setNext(false);
			
		}else if(pageNum > 0 && pageNum < 6) {
			setPrev(false);
			setNext(true);
		}else if(currentblock >= lastblock) {
			setPrev(true);
			setNext(false);
		}else {
			setPrev(true);
			setNext(true);
		}
	}

	private int calcpage(int totalCount, int contentNum) {
		int totalPage = totalCount / contentNum;
		if(totalCount % contentNum > 0) {
			totalPage++;
		}
		
		return totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getContentNum() {
		return contentNum;
	}

	public void setContentNum(int contentNum) {
		this.contentNum = contentNum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = (currentblock*5)-4;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int lastblock, int currentblock) {
		if(lastblock == currentblock) {
			this.endPage = calcpage(getTotalCount(), getContentNum());
		}else {
			this.endPage = getStartPage() + 4;
		}
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getCurrentblock() {
		return currentblock;
	}

	public void setCurrentblock(int pageNum) {
		this.currentblock = pageNum/5;
		if(pageNum%5 > 0) {
			this.currentblock ++;
		}
	}

	public int getLastblock() {
		return lastblock;
	}

	public void setLastblock(int lastblock) {
		  this.lastblock = totalCount / (5*this.contentNum);

	        if(totalCount %(5*this.contentNum)>0){
	            this.lastblock++;
	        }
	}
	
	
	
}
