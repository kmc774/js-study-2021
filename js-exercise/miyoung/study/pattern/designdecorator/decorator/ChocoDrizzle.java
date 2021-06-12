package pattern.designdecorator.decorator;

import pattern.designdecorator.component.Beverage;

public class ChocoDrizzle extends Decorator{

	
	Beverage beverage;
	

	public ChocoDrizzle(Beverage beverage) {
		this.beverage = beverage;
		description = "초코 드리즐 추가";
	}

	@Override
	public String getDescription() {

		return beverage.getDescription() + ", " + description ;
	}

	@Override
	public int cost() {
		
		return beverage.cost() + 300;
	}

}
