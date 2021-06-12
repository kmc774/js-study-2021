package behavioral.strategy.concreteStrategy;

import behavioral.strategy.strategy.TypeStrategy;

public class SuvStrategy implements TypeStrategy{

	@Override
	public void carType() {
		System.out.println("종류 : SUV");
	}

}
