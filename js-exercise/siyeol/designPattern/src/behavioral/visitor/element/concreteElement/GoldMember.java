package behavioral.visitor.element.concreteElement;

import behavioral.visitor.element.Member;
import behavioral.visitor.visitor.Benefit;

public class GoldMember implements Member{

	@Override
	public void getBenefit(Benefit benefit) {
		benefit.getBenefit(this);
	}

}
