package pattern.designdecorator.drink;

import pattern.designdecorator.component.Beverage;

public class MilkTea extends Beverage{

	
	public MilkTea() {
		description = "밀크티";
	}

	
	
	@Override
	public int cost() {
		
		return 4000;
	}

}
