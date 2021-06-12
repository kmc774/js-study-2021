package behavioral.strategy.concreteStrategy;

import behavioral.strategy.strategy.FuelStrategy;

public class GasolineStrategy implements FuelStrategy{

	@Override
	public void fuelType() {
		System.out.println("연료 : 가솔린");
	}
	
}
