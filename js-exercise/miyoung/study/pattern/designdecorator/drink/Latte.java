package pattern.designdecorator.drink;

import pattern.designdecorator.component.Beverage;

public class Latte  extends Beverage{

	
	public Latte() {
		description = "라떼";
	}

	@Override
	public int cost() {
		
		return 3500;
	}

}
