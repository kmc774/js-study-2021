package behavioral.state;


public class Off implements State{
	
	private static Off off;
	private Off() { }
	static {
		off = new Off();
	}
	
	// �̱��� ����
	public static Off getInstance() {
		return off;
	}

	@Override
	public void lightOn(Light light) {
		light.setState(On.getInstance());
		System.out.println("전원 on");
	}

	@Override
	public void lightOff(Light light) {
		System.out.println("반응 없음");
	}

	

	
}
