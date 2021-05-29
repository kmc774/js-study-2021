package pattern.designstrategy.calculate;

import pattern.designstrategy.calculate.context.Calculator;
import pattern.designstrategy.calculate.strategyconcrete.MultiplyCalculateStrategy;
import pattern.designstrategy.calculate.strategyconcrete.SumCalculateStrategy;

public class Client {

	public static void main(String[] args) {
		
		
		Calculator sum = new Calculator("덧셈");
		Calculator multiply = new Calculator("곱셈");
		
		
		
		sum.setCalculateStrategy(new SumCalculateStrategy());
		multiply.setCalculateStrategy(new MultiplyCalculateStrategy());

		
		
		
		
		System.out.println(sum.getMethod() + " 해보기");
		sum.calculate(3, 5);
		
		System.out.println("\n" + multiply.getMethod() + " 해보기");
		multiply.calculate(3, 5);
		
		
		
		
		
	}

}
