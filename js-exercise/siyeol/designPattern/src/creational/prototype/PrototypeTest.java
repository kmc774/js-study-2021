package creational.prototype;

import java.util.List;

/* 프로토타입 패턴은 Original 객체를 새로운 객체에 복사하여 우리의 필요에 따라 수정하는 메커니즘을 제공합니다.
 * 이 패턴은 복사를 위하여 Java에서 제공하는 clone()을 사용합니다.
 * 생성 패턴은 인스턴스를 만드는 절차를 추상화하는 패턴
 * 생성 패턴을 이용하면 무엇이 생성되고, 누가 이것을 생성하며, 이것이 어떻게 생성되는지, 언제 생성할 것인지 결정하는 데 유연성을 확보할 수 있게 됩니다.
 
 * 객체를 복사해서 새로운 객체를 생성하는 패턴
 * 
*/

public class PrototypeTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		Pokemon originPokemon = new Pokemon();
		originPokemon.dataLoad();
		
		Pokemon pokemonClone1 = (Pokemon) originPokemon.clone();	
		Pokemon pokemonClone2 = (Pokemon) originPokemon.clone();
		
		List<String> cloneList1 = pokemonClone1.getcustomerList();
		cloneList1.add("버터풀");
		List<String> cloneList2 = pokemonClone2.getcustomerList();
		cloneList2.remove("피카츄");
		
		
		System.out.println(originPokemon.getcustomerList().toString());
		System.out.println(cloneList1.toString());
		System.out.println(cloneList2.toString());
	}

}
