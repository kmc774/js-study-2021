package pattern.designstrategy.drink.strategyconcrete;

import pattern.designstrategy.drink.strategy.BaseStrategy;


 // Strategy Concrete Class : 실제로 행동들을 구현하고 있다.


public class WaterBaseStrategy implements BaseStrategy{

	@Override
	public void base() {
		// 탄산수를 넣는다. 삼다수를 넣는다.
		System.out.println("물을 넣어줍니다.");
	}

}
