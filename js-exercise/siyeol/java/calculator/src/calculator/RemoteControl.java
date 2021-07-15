package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import calculator.command.Command;
import calculator.command.Input;
import calculator.concreteCommand.InputCancel;
import calculator.concreteCommand.InputConsole;
import calculator.concreteCommand.DivisionCommand;
import calculator.concreteCommand.InputFile;
import calculator.concreteCommand.MultiplyCommand;
import calculator.concreteCommand.PlusCommand;
import calculator.concreteCommand.SubtractCommand;

public class RemoteControl {
	
	Map<String, Command> operatorMap;	//RemoteControl 객체가 생성될 때 같이 연산 객체들도 같이 생성된다 
	Map<String, Input> inputMap;
	
	static List<String> operaters = new ArrayList<>( Arrays.asList("+", "-", "*", "/") );
	
	public RemoteControl() {
		//연산자
		operatorMap = new HashMap<>();
		operatorMap.put("+", new PlusCommand());
		operatorMap.put("-", new SubtractCommand());
		operatorMap.put("*", new MultiplyCommand());
		operatorMap.put("/", new DivisionCommand());
		//입력 방식
		inputMap = new HashMap<>();
		inputMap.put("1", new InputConsole());
		inputMap.put("2", new InputFile());
		inputMap.put("3", new InputCancel());
		
	}
	
	public void startCalculator() {
		while(true) {
			String result = compute();
			if( "C".equals(result) ) {
				System.out.println("== 계산기 종료! ==");
				break;
			}
			System.out.println("= "+result);
			System.out.println("");
		}
	}

	private String compute() {
		
		int result = 0;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("1: 콘솔, 2: 파일선택, 3: 종료");
		System.out.print("-> ");
		String input = scan.nextLine().replaceAll("\\p{Z}+|\\p{Z}+$", "");
		
		String formula = inputMap.get(input).input();
		Map<String, String> split = split(formula);
		//연산자가 없는 경우
		if( split.containsKey("error") ) {
			return split.get("error");
		}
		//정상적으로 계산
		result = doCompute( split.get("sign")
						  , Integer.parseInt(split.get("x"))
						  , Integer.parseInt(split.get("y")) 
						  );
			
		return String.valueOf( result );
		
	}
	
	private Map<String, String> split(String formula) {	//연산자와 피연사자를 분리해 Map으로 반환
		Map<String, String> resultMap = new HashMap<>();
		for(String operater : operaters) {
			if( formula.indexOf(operater) > -1 ) {	//나중에 에러로 처리 (연산자가 있는지 확인)
				String sign = formula.substring( formula.indexOf(operater), formula.indexOf(operater) + 1 );
				String x = formula.substring( 0, formula.indexOf(operater) );
				String y = formula.substring( formula.indexOf(operater) + 1 );
				resultMap.put("sign", sign);
				resultMap.put("x", x);
				resultMap.put("y", y);
				
				if( "".equals(x) || "".equals(y) ) {
					resultMap.put("error", "null number");
				}
				
				return resultMap;
			} else {
				resultMap.put("error", "undefine operatort");
			}
		}
		return resultMap;
	}
	
	private int doCompute(String operater, int x, int y) {	//command 패턴을 이용한 계산 메서드
		return operatorMap.get(operater).compute(x, y);
	}
}
