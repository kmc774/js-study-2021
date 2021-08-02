package calculator.inputimple;

import calculator.ReadFile;
import calculator.command.Input;

public class FileInput implements Input {

	@Override
	public String input() {
		return ReadFile.readFile();
	}

}
