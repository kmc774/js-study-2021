package behavioral.strategy.context;

import behavioral.strategy.strategy.FuelStrategy;
import behavioral.strategy.strategy.TypeStrategy;

public abstract class Car {

	TypeStrategy typeStrategy;
	FuelStrategy fuelStrategy;
	
	
	public void carType() {
		this.typeStrategy.carType();
	}
	
	public void fuelType() {
		this.fuelStrategy.fuelType();
	}
	
	public void setTypeStrategy(TypeStrategy typeStrategy) {
		this.typeStrategy = typeStrategy;
	}


	public void setFuelStrategy(FuelStrategy fuelStrategy) {
		this.fuelStrategy = fuelStrategy;
	}
	
	
}

