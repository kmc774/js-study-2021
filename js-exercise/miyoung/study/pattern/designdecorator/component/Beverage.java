package pattern.designdecorator.component;

/*
 * * 데코레이터 패턴(decorator Pattern)
 *  : 기본적인 기능 이외에 추가할 수 있는 부가적인 기능들의 여러가지 조합을 동적으로 생성하는 구조패턴
 *    - 사용목적 : 기본적인 데이터 이외에 추가되어야 할 데이터가 너무 다양하고 일정하지 않을 때 유용하다.
 *  : 예를 들어 스타벅스라고 했을 때, 고객들이 커스터마이징 음료를 주문하게 될 경우 그때마다 클래스를 형성하게 되면 복잡해지기 때문에
 *    조금 더 발전된 형태로 주문을 받아야 한다.
 *  
 *  : 구조 - component(구성) : 음료의 공통적인 성질을 따로 뺀 것(가격, 메뉴명 등)
 *        - concreteComponent(행동 구성) : 구성을 상속받아 실제 구현 인스터스를 생성
 *        - decorator(데코레이터) : 공통적인 성질 이외의 것
 *        - concreteDecorator(행동 데코레이터)       
 *        
 *  : 단점 : 여러가지 데코레이터 클래스들이 계속 추가되어 클래스가 많아질 염려가 있다.
 *          여러개의 데코레이터가 들어갈 경우에는 최종으로 만들어진 객체가 어떤 속성으로 이루어져있는지 알기 힘들다.
 *          
 */



/*
 * 최상의 부모 클래스, 음료를 만들기 위한 모든 클래스는 해당 클래스를 상속받아야 한다.
 */
public abstract class Beverage {
	
	//패키지 분리하고 싶어서 public으로 변경
	public String description = "null";
	
	public String getDescription() { // 커피의 종류를 반환
		return description;
	}
	
	public abstract int cost();
	
	

}
