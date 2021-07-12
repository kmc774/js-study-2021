package behavioral.visitor.concreteVisitor;

import behavioral.visitor.element.concreteElement.GoldMember;
import behavioral.visitor.element.concreteElement.VipMember;
import behavioral.visitor.visitor.Benefit;

public class PointBenefit implements Benefit{

	@Override
	public void getBenefit(GoldMember member) {
		System.out.println("Point for Gold Member");
	}

	@Override
	public void getBenefit(VipMember member) {
		System.out.println("Point for Vip Member");
	}

}
