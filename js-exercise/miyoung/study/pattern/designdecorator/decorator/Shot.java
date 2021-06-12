package pattern.designdecorator.decorator;

import pattern.designdecorator.component.Beverage;

public class Shot extends Decorator{

	Beverage beverage;
	

	public Shot(Beverage beverage) {
		this.beverage = beverage;
		description = "샷 추가";
	}

	@Override
	public String getDescription() {
		
		return beverage.getDescription() + ", " + description;
	}

	@Override
	public int cost() {
		
		return 500 + beverage.cost();
	}

}
