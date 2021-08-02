package behavioral.strategy;

import behavioral.strategy.concreteStrategy.ElectronicityStrategy;
import behavioral.strategy.concreteStrategy.GasolineStrategy;
import behavioral.strategy.concreteStrategy.SedanStrategy;
import behavioral.strategy.concreteStrategy.SuvStrategy;
import behavioral.strategy.context.Car;
import behavioral.strategy.context.SedanCar;
import behavioral.strategy.context.SuvCar;

/*전략패턴
 *객체들이 할 수 있는 행위 각각에 대해 전략클래스를 생성하고
 *유사한 행위들을 캡슐화 하는 인터페이스를 정의하여
 *객체의 행위를 동적으로 바꾸고 싶은 경우 직접 행위를 수정하지 않고 전략을 바꿔주기만 함으로
 *행위를 유연하게 확장하는 방법 
*/
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
