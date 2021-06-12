package pattern.designdecorator.drink;

import pattern.designdecorator.component.Beverage;

public class Frappuccino extends Beverage {

	
	public Frappuccino() {
		description = "밀크 프라푸치노";
	}

	@Override
	public int cost() {
		
		return 4000;
	}

}
