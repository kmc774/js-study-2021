package pattern.designstrategy.drink.strategyconcrete;

import pattern.designstrategy.drink.strategy.TemperatureStrategy;

public class IceTempStrategy implements TemperatureStrategy{

	@Override
	public void temp() {
		System.out.println("차갑게 만들기 위해 얼음을 넣어줍니다.");
		
	}

}
