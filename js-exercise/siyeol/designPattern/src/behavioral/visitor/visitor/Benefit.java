package behavioral.visitor.visitor;

import behavioral.visitor.element.concreteElement.GoldMember;
import behavioral.visitor.element.concreteElement.VipMember;

public interface Benefit {
	void getBenefit(GoldMember member);
	void getBenefit(VipMember member);
}
