package pattern.designstrategy.drink.context;

import pattern.designstrategy.drink.strategy.BaseStrategy;
import pattern.designstrategy.drink.strategy.TemperatureStrategy;

/*
 * --- Strategy pattern : 전략패턴
 *   : 어떤 Context 에서 사용하게 되는 공통의 행동들을 전략(interface)으로 분리하는 형태,
 *     StrategyConcrete는 하나의 전략 인터페이스를 구현하여 실제의 전략을 구현하고 있고
 *     Context에서 전략에 대한 역할을 Strategy에게 위임함으로써 Client가 전략을 선택할 수 있게 한다.
 *     또한 공통 행동을 전략으로 분리시켰기 때문에 인스턴스 생성시 원하는 전략을 선택할 수 있다.
 *     Context는 해당 전략의 인스턴스를 가지고 있게 되고 어떤 전략을 선택하느냐에 따라
 *     만들어지는 객체의 특성이 달라진다. 
 */


// Context :  전략의 인스턴스를 가지고 있고 어떤 전략을 선택하느냐에 따라 행동이 달라지게 된다.

public class MakeDrink {
	
	private String name;
	private BaseStrategy baseStrategy;
	private TemperatureStrategy temperatureStrategy;
	
	
	
	

	/*
	 * base 전략을 baseStrategy에 위임 : 음료의 drink를 넣어주기
	 */
	public void base() {
		baseStrategy.base();
	}
	
	
	
	
	/*
	 * temp 전략을 temperaturStrategy에 위임 : 음료의 온도를 설정하기
	 */
	public void temp() {
		temperatureStrategy.temp();
	}

	
	
	
	/*
	 * 일반 메소드 -> 데코레이트 패턴 적용 가능
	 */
	public void shot(String shot) {
		System.out.println(shot + " 을 넣어줍니다.");
	}
	
	
	/*
	 * 순서가 중요한 경우에도 사용할 수 있다.
	 * 여러개의 메소드를 한데 묶음
	 * 순서 의존성을 가지게 되는 경우
	 * 템플릿메소드 패턴
	 */
	public void run() {
		this.base();
		this.temp();
		
	}
	
	
	
	
	/*
	 * getter / setter
	 */
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public BaseStrategy getBaseStrategy() {
		return baseStrategy;
	}



	public void setBaseStrategy(BaseStrategy baseStrategy) {
		this.baseStrategy = baseStrategy;
	}



	public TemperatureStrategy getTemperatureStrategy() {
		return temperatureStrategy;
	}



	public void setTemperatureStrategy(TemperatureStrategy temperatureStrategy) {
		this.temperatureStrategy = temperatureStrategy;
	}


	
	
	
	
	
	
	
	

}
