package behavioral.state;



public class Light {

	private State state;

	public Light() {
		this.state = Off.getInstance();
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public void onButton() {
		state.lightOn(this);
	}
	
	public void offButton() {
		state.lightOff(this);
	}
	
/*	
 * 상태 패턴을 적용하지 않은 경우 아래처럼 조건문을 사용해 객체의 모든 상태를 검사해줘야 한다.
	
	public void onButton() {
		if( "on".equals(state) ) {
			System.out.println("반응 없음");
			
		} else if("off".equals(state)) {
			System.out.println("전원 off");
			state = "on";
		}
	}
	
	public void offButton() {
		if( "on".equals(state) ) {
			System.out.println("전원 on");
			state = "on";
			
		} else if("off".equals(state)) {
			System.out.println("반응 없음");
	}
*/	
	

}
