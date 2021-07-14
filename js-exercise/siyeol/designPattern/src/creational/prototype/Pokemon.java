package creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class Pokemon implements Cloneable{	//clone() 메소드를 사용하기 위해 Cloneable 인터페이스 구현

	private List<String> pokemonList;
	
	public Pokemon() {
		pokemonList = new ArrayList();
	}
	
	public Pokemon( List<String> temp ) {
		pokemonList = temp;
	}
	
	public void dataLoad() {	//해당 메소드를 통해 DB에 접속해 데이터를 읽어 왔다고 가정
		System.out.println("DB 접속 -----");
		pokemonList.add("피카츄");
		pokemonList.add("라이츄");
		pokemonList.add("파이리");
		pokemonList.add("꼬부기");
	}
	
	public List<String> getcustomerList() {
		return pokemonList;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// super.clone()를 사용할 경우 얕은 복사가 되기 때문에 깊은 복사를 사용하기 위해 메소드를 재정의 했다
		
		List<String> temp = new ArrayList<String>();
		for( String pokemon : pokemonList ) {
			temp.add( pokemon );
		}

		return new Pokemon( temp );
		
		/* 얕은 복사
		return super.clone();
		*/
	}


}
