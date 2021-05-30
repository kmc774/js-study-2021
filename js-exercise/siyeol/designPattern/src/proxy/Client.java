package proxy;
/*
 * 프록시 객체는 원래 객체를 감싸고 있는 객체
 * 접근을 제어하고 싶거나, 부가 기능을 추가하고 싶을 때 사용 (권한에 따른 접근제어 등..)
*/

public class Client {

	public static void main(String[] args) {
		Service service = new Proxy();
		
		System.out.println( service.runSomething() );
		
	}

}
