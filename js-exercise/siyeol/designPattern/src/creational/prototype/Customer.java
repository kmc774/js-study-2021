package creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class Customer implements Cloneable{

	private List<String> customerList;
	
	public Customer() {
		customerList = new ArrayList();
	}
	
	public Customer( List<String> temp ) {
		customerList = temp;
	}
	
	public void dataLoad() {
		System.out.println("DB 접속 -----");
		customerList.add("피카츄");
		customerList.add("라이츄");
		customerList.add("파이리");
		customerList.add("꼬부기");
	}
	
	public List<String> getcustomerList() {
		return customerList;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// super.clone()를 사용할 경우 얕은 복사가 되기 때문에 깊은 복사를 사용하기 위해 메소드를 재정의 했다
		
		List<String> temp = new ArrayList<String>();
		for( String customer : customerList ) {
			temp.add( customer );
		}

		return new Customer( temp );
		
		/* 얕은 복사
		return super.clone();
		*/
	}


}
