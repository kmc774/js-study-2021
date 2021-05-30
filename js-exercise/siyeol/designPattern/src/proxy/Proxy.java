package proxy;

public class Proxy implements Service{

	@Override
	public String runSomething() {
		
		Service serviceImpl = new ServiceImpl();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("before 추가!!  \n");
		sb.append(serviceImpl.runSomething());
		sb.append("\nafter 추가!!");
		
		return sb.toString();
	}

	
}
