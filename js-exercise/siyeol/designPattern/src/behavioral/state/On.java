package behavioral.state;

public class On implements State {
	
	private static On on;
	private On() { }
	static {
		on = new On();
	}
	// 싱글톤으로 생성
	public static On getInstance() {
		return on;
	}

	@Override
	public void lightOn(Light light) {
		light.setState( SleepMode.getinstance() );
		System.out.println("수면 모드 on");
		
	}

	@Override
	public void lightOff(Light light) {
		light.setState(Off.getInstance());
		System.out.println("전원 off");
	}

	
}
