package pattern.designstrategy.drink;

import pattern.designstrategy.drink.context.MakeDrink;
import pattern.designstrategy.drink.strategyconcrete.HotTempStrategy;
import pattern.designstrategy.drink.strategyconcrete.IceTempStrategy;
import pattern.designstrategy.drink.strategyconcrete.MilkBaseStrategy;
import pattern.designstrategy.drink.strategyconcrete.WaterBaseStrategy;

public class Client {

	public static void main(String[] args) {
		
		// americano 객체 생성
		MakeDrink americano = new MakeDrink();
		// milktea 객체 생성
		MakeDrink milkTea = new MakeDrink();
		// latte
		MakeDrink latte = new MakeDrink();
		
		
		// MakeDrink가 어떻게 행동하게 할지 전략 선택해주기
		americano.setName("아메리카노");
		americano.setBaseStrategy(new WaterBaseStrategy()); // base : 물
		americano.setTemperatureStrategy(new IceTempStrategy()); // temp : 아이스로
		
		milkTea.setName("밀크티");
 		milkTea.setBaseStrategy(new MilkBaseStrategy()); // base : 우유
 		milkTea.setTemperatureStrategy(new HotTempStrategy()); // temp : 핫으로
		
 		
 		latte.setName("라떼");
 		latte.setBaseStrategy(new MilkBaseStrategy());
 		latte.setTemperatureStrategy(new HotTempStrategy());
 		
		
 		// MakeDrink가 행동한 전략에 따라 객체가 어떻게 만들어졌는지 확인해보기
 		System.out.println(">>> " + americano.getName() + " 주문이 들어왔습니다.");
 		americano.run();
 		americano.shot("3샷");
 		System.out.println("완성 !!!");
 		
 		
 		
 		System.out.println("\n>>> " + milkTea.getName() + " 주문이 들어왔습니다.");
 		milkTea.run();
 		System.out.println("완성 !!!");
		
 		
 		System.out.println("\n>>> " + latte.getName() + " 주문이 들어왔습니다.");
 		latte.run();
 		System.out.println("완성 !!");
 		
 		
 		
	}

}
