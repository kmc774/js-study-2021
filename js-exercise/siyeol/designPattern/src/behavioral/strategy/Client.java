package behavioral.strategy;

import behavioral.strategy.concreteStrategy.ElectronicityStrategy;
import behavioral.strategy.concreteStrategy.GasolineStrategy;
import behavioral.strategy.concreteStrategy.SedanStrategy;
import behavioral.strategy.concreteStrategy.SuvStrategy;
import behavioral.strategy.context.Car;
import behavioral.strategy.context.SedanCar;
import behavioral.strategy.context.SuvCar;

public class Client {

	public static void main(String[] args) {
		Car suv = new SuvCar();
		Car sedan = new SedanCar();
		
		suv.setTypeStrategy( new SuvStrategy() );
		suv.setFuelStrategy( new GasolineStrategy() );
		
		sedan.setTypeStrategy( new SedanStrategy() );
		sedan.setFuelStrategy( new ElectronicityStrategy() );
		
		System.out.println("-- 차량 스펙 --");
		suv.carType();
		suv.fuelType();
		
		System.out.println("-- 차량 스펙 --");
		sedan.carType();
		sedan.fuelType();
		

	}
}
