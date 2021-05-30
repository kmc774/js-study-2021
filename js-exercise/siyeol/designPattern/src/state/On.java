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
		System.out.println("���� ����");
		
	}

	@Override
	public void lightOff(Light light) {
		light.setState(Off.getInstance());
		System.out.println("���� off");
	}

	
}
