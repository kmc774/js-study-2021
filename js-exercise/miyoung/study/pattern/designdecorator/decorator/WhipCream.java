package pattern.designdecorator.decorator;

import pattern.designdecorator.component.Beverage;

public class WhipCream extends Decorator{

	
	Beverage beverage;
	
	
	public WhipCream(Beverage beverage) { // 생성자
		description = "휘핑 크림 추가";
		this.beverage = beverage;
	}

	
	
	
	@Override
	public String getDescription() { // Decorator 에 있던 Method
		
		return beverage.getDescription() + ", " + description;
	}

	@Override
	public int cost() { //최상위 부모클래스(Beverage)에 있던 Method ---> 상속의 상속
		
		return beverage.cost() + 500; //기존의 beverage cost에 cream cost 추가된 형태로 return;
	}

}
