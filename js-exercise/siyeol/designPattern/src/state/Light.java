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
 * ���� ������ �ƴ� ���ǹ����� ó�� ���� ���
	
	public void onButton() {
		if( "on".equals(state) ) {
			System.out.println("���� ����");
			
		} else if("off".equals(state)) {
			System.out.println("���� off");
			state = "on";
		}
	}
	
	public void offButton() {
		if( "on".equals(state) ) {
			System.out.println("���� on");
			state = "on";
			
		} else if("off".equals(state)) {
			System.out.println("���� ����");
	}
*/	
	

}
