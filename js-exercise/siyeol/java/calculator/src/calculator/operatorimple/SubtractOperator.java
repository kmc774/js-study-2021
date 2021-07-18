package calculator.operatorimple;

import calculator.command.Operator;

public class SubtractOperator implements Operator {

	@Override
	public int compute(int x, int y) {
		return x-y;
	}

}