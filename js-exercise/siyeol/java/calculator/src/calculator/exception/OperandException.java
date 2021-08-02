package calculator.exception;

public class OperandException extends RuntimeException{
    public OperandException(String message) {
        super(message);
    }

    public OperandException(String message, Throwable cause) {
        super(message, cause);
    }
}
