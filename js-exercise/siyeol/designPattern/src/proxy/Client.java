package proxy;
/*
 * ���Ͻ� ��ü�� ���� ��ü�� ���ΰ� �ִ� ��ü
 * ������ �����ϰ� �Ͱų�, �ΰ� ����� �߰��ϰ� ���� �� ��� (���ѿ� ���� �������� ��..)
*/

public class Client {

	public static void main(String[] args) {
		Service service = new Proxy();
		
		System.out.println( service.runSomething() );
		
	}

}
