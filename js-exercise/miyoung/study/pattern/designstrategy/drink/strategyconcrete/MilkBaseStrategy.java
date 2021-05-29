package pattern.designstrategy.drink.strategyconcrete;

import pattern.designstrategy.drink.strategy.BaseStrategy;

public class MilkBaseStrategy implements BaseStrategy {

	@Override
	public void base() {
		// 귀리 우유를 넣는다, 오트우유를 넣는다.
		System.out.println("우유를 넣어줍니다.");
		
	}

}
