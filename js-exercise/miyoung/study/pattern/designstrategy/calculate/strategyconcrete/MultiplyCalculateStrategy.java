package pattern.designstrategy.calculate.strategyconcrete;

import pattern.designstrategy.calculate.strategy.CalculateStrategy;

public class MultiplyCalculateStrategy implements CalculateStrategy {

	@Override
	public void calculate(int num1, int num2) {
		System.out.println("계산 결과는 " + (num1 * num2) + " 입니다.");
		
	}

}
