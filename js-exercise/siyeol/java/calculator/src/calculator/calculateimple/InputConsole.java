package calculator.calculateimple;

import java.util.Scanner;

import calculator.command.Input;

public class InputConsole implements Input{

	@Override
	public String input() {
		Scanner scan = new Scanner(System.in);
		System.out.println("계산식을 입력하세요");
		System.out.print("-> ");
		return scan.nextLine().replaceAll("\\p{Z}+|\\p{Z}+$", "");
	}

}
