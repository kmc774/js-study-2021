package calculator.calculateimple;

import calculator.command.CalculateCommand;

public class SubtractCalculateCommand implements CalculateCommand {

	@Override
	public int compute(int x, int y) {
		return x-y;
	}

}