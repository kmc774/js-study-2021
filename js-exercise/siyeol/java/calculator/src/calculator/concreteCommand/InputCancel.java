package calculator.concreteCommand;

import calculator.command.Input;

public class InputCancel implements Input{

	@Override
	public String input() {
		return "C";
	}

}
