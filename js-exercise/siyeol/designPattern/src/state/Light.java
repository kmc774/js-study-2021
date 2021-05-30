package state;



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
 * 상태 패턴이 아닌 조건문으로 처리 했을 경우
	
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
