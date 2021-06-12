package pattern.designdecorator;

import pattern.designdecorator.component.Beverage;
import pattern.designdecorator.component.Pizza;
import pattern.designdecorator.decorator.ChocoDrizzle;
import pattern.designdecorator.decorator.Meat;
import pattern.designdecorator.decorator.Shot;
import pattern.designdecorator.decorator.Shrimp;
import pattern.designdecorator.decorator.WhipCream;
import pattern.designdecorator.drink.Americano;
import pattern.designdecorator.drink.Frappuccino;
import pattern.designdecorator.drink.Latte;
import pattern.designdecorator.drink.MilkTea;

public class Customer {

	public static void main(String[] args) {

		
		Beverage bev  = new Americano();
		
		System.out.println("메뉴 : " + bev.getDescription());
		System.out.println("가격 : " + bev.cost() + "원");
		System.out.println("-------------------------------------");
		//-------------------- 기본 아메리카노 주문 ----------------------
		
		bev = new Shot(bev); //음료의 정보를 Shot에 넘겨주기;
									   //기본으로 만든 음료의 속성 + deco가 입혀진다.
		System.out.println("메뉴 : " + bev.getDescription());
		System.out.println("가격 : " + bev.cost() + "원");
		System.out.println("---------------------------------------------");
		//-------------------- 아메리카노 + 샷추가 ---------------------
		
		
		
		Beverage beverage = new MilkTea();
		System.out.println("메뉴 : " + beverage.getDescription());
		System.out.println("가격 : " + beverage.cost() + "원");
		System.out.println("---------------------------------------------");
		
		beverage = new WhipCream(beverage);
		System.out.println("메뉴 : " + beverage.getDescription());
		System.out.println("가격 : " + beverage.cost() + "원");
		System.out.println("---------------------------------------------");
		
		
		beverage = new Shot(beverage);
		System.out.println("메뉴 : " + beverage.getDescription());
		System.out.println("가격 : " + beverage.cost() + "원");
		System.out.println("---------------------------------------------");
		
		
		
		
		Pizza pizza = new Shrimp (new Meat(new Pizza()));
		System.out.println("메뉴 : " + pizza.getDescription());
		System.out.println("가격 : " + pizza.cost() + "원");
		System.out.println("---------------------------------------------");
		
		
		
		
		
		
	}

}
