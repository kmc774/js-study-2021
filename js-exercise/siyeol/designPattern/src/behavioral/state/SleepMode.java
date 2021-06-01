package behavioral.state;

public class SleepMode implements State {
	
	private static SleepMode sleepMode;
	
	private SleepMode() { }
	
	static {
		sleepMode = new SleepMode();
	}
	// 싱글톤으로 생성
	public static SleepMode getinstance() {
		return sleepMode;
	}

	@Override
	public void lightOn(Light light) {
		light.setState(new SleepMode());
		System.out.println("전원 on( 수면 모드 off )");
	}

	@Override
	public void lightOff(Light light) {
		light.setState(Off.getInstance());
		System.out.println("전원 Off");
	}

	
}
