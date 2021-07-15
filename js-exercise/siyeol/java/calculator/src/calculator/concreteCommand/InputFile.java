package calculator.concreteCommand;

import calculator.ReadFile;
import calculator.command.Input;

public class InputFile implements Input {

	@Override
	public String input() {
		return ReadFile.readFile();
	}

}
