package state;

public class On implements State {
	
	private static On on;
	private On() { }
	static {
		on = new On();
	}
	
	public static On getInstance() {
		return on;
	}

	@Override
	public void lightOn(Light light) {
		System.out.println("반응 없음");
		
	}

	@Override
	public void lightOff(Light light) {
		light.setState(Off.getInstance());
		System.out.println("전원 off");
	}

	
}
