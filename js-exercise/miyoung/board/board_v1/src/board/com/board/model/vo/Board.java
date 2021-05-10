package board.com.board.model.vo;

public class Board {
	
	
	private int bdIdx;
	private String Title;
	private String content;
	

	public Board() {
		
	}



	public Board(int bdIdx, String title, String content) {
		super();
		this.bdIdx = bdIdx;
		Title = title;
		this.content = content;
	}



	public int getBdIdx() {
		return bdIdx;
	}


	public void setBdIdx(int bdIdx) {
		this.bdIdx = bdIdx;
	}


	public String getTitle() {
		return Title;
	}


	public void setTitle(String title) {
		Title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "Board [bdIdx=" + bdIdx + ", Title=" + Title + ", content=" + content + "]";
	}
	
	
	

}
