package calculator.concreteCommand;

import calculator.command.Command;

public class PlusCommand implements Command{

	@Override
	public int compute(int x, int y) {
		return x+y;
	}

}
