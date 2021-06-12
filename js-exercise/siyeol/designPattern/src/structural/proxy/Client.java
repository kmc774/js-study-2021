package structural.proxy;
/*
 * 프록시 패턴
*/

public class Client {

	public static void main(String[] args) {
		Service service = new Proxy();
		
		System.out.println( service.runSomething() );
		
	}

}
