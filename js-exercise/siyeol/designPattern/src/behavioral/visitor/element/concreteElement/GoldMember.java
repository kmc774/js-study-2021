package behavioral.visitor.element.concreteElement;

import behavioral.visitor.element.Member;
import behavioral.visitor.visitor.Benefit;

public class GoldMember implements Member{

	@Override
	public void getBenefit(Benefit benefit) {
		benefit.getBenefit(this);
	}
	
	/*
	public void point() { System.out.println("Point for Gold Member"); }
	public void discount() { System.out.println("Discount for Gold Member"); }
	*/
}
