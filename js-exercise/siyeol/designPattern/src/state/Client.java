package state;

/* 상태 패턴(state)
 * 동일한 동작을 객체의 상태에 따라 다르게 처리할 때 사용 ( 상태 : 전원 객체의 경우 ON, OFF 가 상태가 될 수 있다 )
 * 비슷한 패턴으로는 전략 패턴이 있다
 * 전략 패턴은 상속을 대체하려는 목적으로, 스테이트 패턴은 코드내의 조건문들을 대체하려는 목적
 * 즉 각각 조건이 될 수 있는 것들을 클래스로 만든다
 
 * 상태 패턴에서 상태가 추가 됐을 경우
 * Light 클래스의 수정은 최소화 하고, 상태 클래스만 추가해주면 된다
  
 * 요약 : 변하는 것은 잘 변하지 않는 것과 분리해라. 즉, 변하는 녀석들을 캡슐화해라! 
 
 * 문제점 : 상태가 변할때 마다 상태 객체를 새로 생성하기 때문에 메모리 낭비 발생
 * 해결  : 싱글톤 패턴을 적용해서 생성된 인스턴스를 재사용 하도록 한다		  
*/

public class Client {

	public static void main(String[] args) {

		Light light = new Light();
		
		light.onButton();	//off -> on '전원 on' 
		light.onButton();	//on -> on '반응 없음'  
		light.offButton();	//on -> off '전원 off' 
		
		
	}
	
	

}
