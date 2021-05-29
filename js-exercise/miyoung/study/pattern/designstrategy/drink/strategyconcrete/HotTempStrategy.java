package pattern.designstrategy.drink.strategyconcrete;

import pattern.designstrategy.drink.strategy.TemperatureStrategy;

public class HotTempStrategy implements TemperatureStrategy{

	@Override
	public void temp() {
		System.out.println("뜨겁게 만들기 위해 스팀을 넣어줍니다.");
		
	}

}
