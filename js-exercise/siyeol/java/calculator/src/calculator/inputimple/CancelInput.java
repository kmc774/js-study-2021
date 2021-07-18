package calculator.inputimple;

import calculator.command.Input;

public class CancelInput implements Input{

	@Override
	public String input() {
		return "C";
	}

}
