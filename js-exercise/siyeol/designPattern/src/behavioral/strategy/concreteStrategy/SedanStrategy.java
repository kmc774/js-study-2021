package behavioral.strategy.concreteStrategy;

import behavioral.strategy.strategy.TypeStrategy;

public class SedanStrategy implements TypeStrategy{

	@Override
	public void carType() {
		System.out.println("종류 : 세단");
	}

}
