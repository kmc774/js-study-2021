package pattern.designstrategy.calculate.context;

import pattern.designstrategy.calculate.strategy.CalculateStrategy;

/*
 * 원하는 계산식을 해주는 계산기 클래스
 */
public class Calculator {
	
	
	private String method;
	private CalculateStrategy calculateStrategy;
	
	
	
	public Calculator(String method) {
		this.method = method;
	}
	
	
	
	public void calculate(int num1, int num2) {
		calculateStrategy.calculate(num1, num2);
	}


	
	
	
	
	
	

	public String getMethod() {
		return method;
	}



	public CalculateStrategy getCalculateStrategy() {
		return calculateStrategy;
	}



	public void setCalculateStrategy(CalculateStrategy calculateStrategy) {
		this.calculateStrategy = calculateStrategy;
	}
	
	
	
	
	
	

}
