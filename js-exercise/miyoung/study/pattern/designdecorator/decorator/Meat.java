package pattern.designdecorator.decorator;

import pattern.designdecorator.component.Pizza;

public class Meat extends DecoratorPizza{
	
	
	Pizza pizza;
	
	
	public Meat(Pizza pizza) {
		this.pizza = pizza;
		description = " + 불고기 토핑 추가";
	}

	@Override
	public String getDescription() {
		
		return pizza.getDescription() + description;
	}

	@Override
	public int cost() {
		return pizza.cost() + 1000;
	}

}
