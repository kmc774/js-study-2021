package structural.proxy;

public class Proxy implements Service{

	@Override
	public String runSomething() {
		
		Service serviceImpl = new ServiceImpl();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("before !!  \n");
		sb.append(serviceImpl.runSomething());
		sb.append("\nafter !!");
		
		return sb.toString();
	}

	
}
