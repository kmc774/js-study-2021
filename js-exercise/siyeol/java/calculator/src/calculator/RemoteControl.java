package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import calculator.command.Command;
import calculator.concreteCommand.DivisionCommand;
import calculator.concreteCommand.MultiplyCommand;
import calculator.concreteCommand.PlusCommand;
import calculator.concreteCommand.SubtractCommand;

public class RemoteControl {
	
	Map<String, Command> constMap;	//RemoteControl 객체가 생성될 때 같이 연산 객체들도 같이 생성된다 
	
	static List<String> operaters = new ArrayList<>( Arrays.asList("+", "-", "*", "/") );
	
	public RemoteControl() {
		constMap = new HashMap<>();
		constMap.put("+", new PlusCommand());
		constMap.put("-", new SubtractCommand());
		constMap.put("*", new MultiplyCommand());
		constMap.put("/", new DivisionCommand());
	}
	
	public void startCalculator() {
		while(true) {
			String result = compute();
			if( "C".equals(result) ) {
				System.out.println("== 계산기 종료! ==");
				break;
			}
			System.out.println(result);
			System.out.println("");
		}
	}

	private String compute() {
		
		int result = 0;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("1: 콘솔, 2: 파일선택, 3: 종료");
		System.out.print("-> ");
		String input = scan.nextLine().replaceAll("\\p{Z}+|\\p{Z}+$", "");
		
		if( "1".equals(input) ) {
			//콘솔
			String formula = scan.nextLine().replaceAll("\\p{Z}+|\\p{Z}+$", "");
			Map<String, String> split = split(formula);
			result = doCompute( split.get("sign")
							  , Integer.parseInt(split.get("x"))
							  , Integer.parseInt(split.get("y")) 
							  );
			
		} else if( "2".equals(input) ) {
			//파일 선택
			String formula = ReadFile.readFile();
			Map<String, String> split = split(formula);
			result = doCompute( split.get("sign")
							  , Integer.parseInt(split.get("x"))
							  , Integer.parseInt(split.get("y")) 
							  );
			
		} else if("3".equals(input)) {
			//계산기 종료
			return "C";
		}
		
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
			}
		}
		return resultMap;
	}
	
	private int doCompute(String operater, int x, int y) {	//command 패턴을 이용한 계산 메서드
		return constMap.get(operater).compute(x, y);
	}
}
