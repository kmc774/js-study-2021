package behavioral.visitor.element.concreteElement;

import behavioral.visitor.element.Member;
import behavioral.visitor.visitor.Benefit;

public class VipMember implements Member{

	@Override
	public void getBenefit(Benefit benefit) {
		benefit.getBenefit(this);
	}

	/*bad example-1
	public void point() { System.out.println("Point for  Member"); }
	public void discount() { System.out.println("Discount for Gold Member"); }
	*/
}
