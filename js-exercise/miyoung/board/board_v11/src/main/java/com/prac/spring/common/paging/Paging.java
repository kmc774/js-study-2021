package com.prac.spring.common.paging;

public class Paging {
	
		//�Է¹��� ��
		private String type; // ����¡ ó���� �׸�
		private int currentPage; //���� ������
		private int total; //��ü �Խù� ��
		private int cntPerPage; //�������� �Խù� ��
		private int blockCnt; //�� �ٿ� ǥ�� �� ��� ����
		 
		
		//����ؾ��ϴ� ��
		private int lastPage; //������ ������
		private int blockStart; //���� ���
		private int blockEnd; //�� ���
		private int prev; //���� ��ư�� �� ��
		private int next; //���� ��ư�� �� ��
		
		
		//�������� between���� ������ rownum��
		private int startRow;
		private int endRow;
		
		
		
		public Paging() {
			super();
			// TODO Auto-generated constructor stub
		}   

		
		
		private Paging(PagingBuilder builder) {
			this.type = builder.type;
			this.currentPage = builder.currentPage;
			this.total = builder.total;
			this.cntPerPage = builder.cntPerPage;
			this.blockCnt = builder.blockCnt;
			calLastPage();
			calBlockStartAndEnd();
			calQueryStartAndEnd();
			calPrevAndNext();
		}
		
		
		
		private void calLastPage() {
			lastPage = (total-1)/cntPerPage + 1;
		}
		
		
		
		private void calBlockStartAndEnd() {
			//���� ������ ������ ���� blockCnt�� ��� �� ���� ū ���� 1�� ���ϸ� ���۰�
			blockStart = ((currentPage-1)/blockCnt) * blockCnt + 1;
			blockEnd = blockStart + blockCnt -1;
			blockStart = blockStart < 1? 1:blockStart;
			blockEnd = blockEnd > lastPage? lastPage:blockEnd;
		}    
		
		
		private void calQueryStartAndEnd() {
			endRow = currentPage * cntPerPage;
			startRow = endRow - cntPerPage+1;
		}
		
		
		private void calPrevAndNext() {
			prev = currentPage == 1? currentPage:currentPage-1;
			next = currentPage == lastPage? lastPage:currentPage+1;
		}
		
		
		public static PagingBuilder builder() {
			return new PagingBuilder();
		}
		
		
		
		public static class PagingBuilder{
			//�Է¹��� ��
			private String type; // ����¡ ó���� �׸�
			private int currentPage; //���� ������
			private int total; //��ü �Խù� ��
			private int cntPerPage; //�������� �Խù� ��
			private int blockCnt; //�� �ٿ� ǥ�� �� ��� ����
			
			
			public PagingBuilder type(String val) {
				this.type = val;
				return this;
			}
			
			public PagingBuilder currentPage(int val) {
				this.currentPage = val;
				return this;
			}
			
			public PagingBuilder total(int val) {
				this.total = val;
				return this;
			}
			
			public PagingBuilder cntPerPage(int val) {
				this.cntPerPage = val;
				return this;
			}
			
			public PagingBuilder blockCnt(int val) {
				this.blockCnt = val;
				return this;
			}
			
			public Paging build() {
				return new Paging(this);
			}
		}
		
		
		
		
		
		public String getType() {
			
			return type;
		}
		
		public int getCurrentPage() {
			
			return currentPage;
		}
		
		public int getTotal() {
			
			return total;
		}
		
		public int getCntPerPage() {
			
			return cntPerPage;
		}
		
		public int getBlockCnt() {
			
			return blockCnt;
		}
		
		public int getLastPage() {
			
			return lastPage;
		}
		
		public int getBlockStart() {
			
			return blockStart;
		}
		
		public int getBlockEnd() {
			
			return blockEnd;
		}
		
		public int getPrev() {
			
			return prev;
		}
		
		public int getNext() {
			
			return next;
		}
		
		public int getStartRow() {
			
			return startRow;
		}
		
		public int getEndRow() {
			
			return endRow;
		}
	
	

}
