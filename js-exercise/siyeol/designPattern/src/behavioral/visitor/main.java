package behavioral.visitor;

import behavioral.visitor.concreteVisitor.DiscountBenefit;
import behavioral.visitor.concreteVisitor.FreeRentBenefit;
import behavioral.visitor.concreteVisitor.PointBenefit;
import behavioral.visitor.element.Member;
import behavioral.visitor.element.concreteElement.GoldMember;
import behavioral.visitor.element.concreteElement.VipMember;
import behavioral.visitor.visitor.Benefit;

public class main {
	
	/*
	 * Visitor 패턴( 방문자 패턴 )
	 * 실제 로직을 가지고 있는 객체(Visitor)가 로직을 적용할 객체(Element)를 방문하면서 실행하는 패턴
	 * 로직과 구조를 분리하는 패턴
	 * 로직과 구조가 분리되면 구조를 수정하지 않고 새로운 동작을 기존 객체 구조에 추가할 수 있다.
	 * 대상 객체가 잘 바뀌지 않고(특히 개수), 적용할 알고리즘이 추가될 가능성이 많은 상황일 때 사용을 고려해봐야 한다.
	  ( Gold, Vip, Green 으로 고정이거나 추가될 가능성이 작으면서 혜택은 앞으로 계속해서 추가될 가능성이 있을 때를 말한다 
	    왜냐하면 Member가 추가되면 모든 Benefit 클래스를 수정해야 하기 때문이다. )
	 
	 도메인에서 사용하는 프로그램을 만들다보면 객체와 객체간 조합 n * n 을 구현해야 할 때가 많았다. 
	 그럴때 마다 instanceof 와 같은 연산자로 간단하게 해결해 버리고 말았다. 
	 하지만 시간이 지나다 보니 if 문이 남발되고 유지보수하기가 점점 힘들어져서 해결책을 찾던 도중 Double dispatch 기법을 알게되고 이걸 활용한 비지터 패턴을 공부하게 되었다. 
	 다른 패턴에 비해 다소 복잡한것 같기도하고 Double dispatch 기법을 통해 하다보니 한번에 딱 와닫지가 않았는데 처음부터 문제를 해결해 나가는 과정을 보면서 왜 이렇게 써야하는지
	 , 주는 이점이 무엇인지 더 잘 알 수 있게 된 계기가 된것 같다.
	 이 글을 보는분도 모두 비지터 패턴에 대한 원리를 더 이해를 잘 할 수 있었으면 좋겠다.
	 */

	public static void main(String[] args) {
		Member goldMember = new GoldMember();
		Member vipMember = new VipMember();
		Benefit pointBenefit = new PointBenefit();
		Benefit discountBenefit = new DiscountBenefit();
		//-------------------혜택 추가
		Benefit freeRentBenefit = new FreeRentBenefit();
		
		goldMember.getBenefit(pointBenefit);
		vipMember.getBenefit(pointBenefit);
		goldMember.getBenefit(discountBenefit);
		vipMember.getBenefit(discountBenefit);
		//---------------------
		goldMember.getBenefit(freeRentBenefit);
		vipMember.getBenefit(freeRentBenefit);
		
		
		/*bad example-1
		 * 1. 혜택을 주기위헤 명시적으로 메소드를 호출해야 한다( .point() )
		 * 2. 혜택이 늘어났을 때 모든 멤버들에게 그 혜택을 구현했다는 보장이 없다 (혜택을 누락하는 실수를 할수 있다)
        Member goldMember = new GoldMember();
        Member vipMember = new VipMember();

        goldMember.point();
        vipMember.point();
        goldMember.discount();
        vipMember.discount();
		*/
	}

}
