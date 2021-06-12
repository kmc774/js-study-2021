package pattern.designdecorator.decorator;

import pattern.designdecorator.component.Pizza;

public class Shrimp extends Pizza{

	
	Pizza pizza;
	
	
	public Shrimp(Pizza pizza) {
		this.pizza = pizza;
		description = " + 새우 토핑 추가";
	}



	public String getDescription () {
		
		return pizza.getDescription() + description;
	}
	
	
	
	@Override
	public int cost() {
		return pizza.cost() + 1500;
	}

}
