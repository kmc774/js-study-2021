package calculator;

import calculator.command.Input;
import calculator.command.Operator;
import calculator.exception.OperandException;
import calculator.exception.OperatorException;
import calculator.operatorimple.DivisionOperator;
import calculator.operatorimple.MultiplyOperator;
import calculator.operatorimple.PlusOperator;
import calculator.operatorimple.SubtractOperator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Calculator {
	
	Map<String, Operator> operatorMap;	//RemoteControl 객체가 생성될 때 같이 연산 객체들도 같이 생성된다
	Map<String, Input> inputMap;
	
	public Calculator() {
		//연산자
		operatorMap = new HashMap<>();
		operatorMap.put("+", new PlusOperator());
		operatorMap.put("-", new SubtractOperator());
		operatorMap.put("*", new MultiplyOperator());
		operatorMap.put("/", new DivisionOperator());
		//입력 방식
		inputMap = new HashMap<>();
		inputMap.put("1", new ConsoleInput());
		inputMap.put("2", new FileInput());
	}
	
	public void startCalculator() {
		while(true) {
			// 커맨드 입력받기
			String command = readUserCommand();

			// 입력식 입력받기
			//TODO: 콘솔 혹은 파일로부터 입력
			String expression = readExpression(command);

			// 계산하기.
			//TODO: 위에서 입력받은 표현식으로 계산.
			try {
				int result = calculate(expression);
				System.out.println("result = " + result); //출력(기능이 추가되면 메서드로 생성)
			} catch (OperatorException | OperandException e) {
				System.out.println(e.getMessage() + "\n");
			}
		}
	}

	/**
	 * 계산식 입력 방식 선택 메소드
	 * 1 : 콘솔
	 * 2 : 파일선택
	 * 3 : 종료
	 */
	String readUserCommand(){
		System.out.println("1: 콘솔, 2: 파일선택, q: 종료");
		System.out.print("-> ");
		Scanner scan = new Scanner(System.in);
		String userCommand = scan.nextLine().replaceAll("\\p{Z}+|\\p{Z}+$", "");

		if( "q".equals(userCommand) ) {
			System.out.println("== 계산기 종료! ==");
			System.exit(-1);
		}

		return userCommand;
	}

	/**
	 * 계산식 입력 메소드
	 * param 사용자에게 입력 받은 계산식 입력 방법 ("1", "2")
	 * return 선택한 입력 방법을 통해 입력 받은 계산식
	 */
	private String readExpression(String inputSourceType){
		//1 이라면, 콘솔에서 입력
		//2 라면, 파일에서 입력.
		return inputMap.get(inputSourceType).input();
	}

	/**
	 * 계산식을 받아 다른 메소드들을 사용해 결과값을 반환하는 메소드
	 * param 문자열 형태인 계산식
	 * return 정수형 형태인 계산식의 결과 값
	 */
	private int calculate(String expression) throws OperatorException, OperandException{
		ParsedExpression parsedExpression = parse(expression);
		return doCompute( parsedExpression );
	}


	/**
	 * 계산식을 입력받아 피연산자, 연산자로 나누는 메서드
	 * param 문자열 형태의 계산식
	 * return 피연산자와 연산자를 나눈 후 ParsedExpression 내부 클래스에 담아서 반환
	 */
	private ParsedExpression parse(String expression) {	//연산자와 피연사자를 분리해 Map으로 반환
		Iterator<String> it =  operatorMap.keySet().iterator();
		while(it.hasNext()){

			String operater = it.next();
			if (expression.indexOf(operater) > -1) {    //나중에 에러로 처리 (연산자가 있는지 확인)
				String operator = expression.substring(expression.indexOf(operater), expression.indexOf(operater) + 1);
				try {
					int operand1 = Integer.parseInt( expression.substring(0, expression.indexOf(operater)) );
					int operand2 = Integer.parseInt( expression.substring(expression.indexOf(operater) + 1) );

					ParsedExpression parsedExpression = new ParsedExpression( operator, operand1, operand2 );
					return parsedExpression;
				}catch( NumberFormatException e ){
					throw new OperandException("계산될 수들을 정확히 입력해 주세요.");
				}
			}

		}

		throw new OperatorException("연산자를 입력하지 않았거나 정의된 연산자가 아닙니다.");

	}

	/**
	 * 피연산자와 연산자로 나눠진 식을 입력받아 계산값을 반환하는 메소드
	 * param 피연산자와 연산자가 담긴 ParsedExpression 내부 클래스
	 * return 계산의 결과 값
	 */
	private int doCompute(ParsedExpression parsedExpression) {	//command 패턴을 이용한 계산 메서드
		Operator operator = operatorMap.get(parsedExpression.operator);
		return operator.compute(parsedExpression.operands[0], parsedExpression.operands[1]);
	}

	/**
	 * 피연산자와 연산자가 담길 내부 클래스
	 */
	class ParsedExpression {
		String operator;
		int[] operands;

		public ParsedExpression(String operator, int operand1,int operand2 ){
			this.operator = operator;
			this.operands = new int[]{operand1, operand2};
		}
	}
}
