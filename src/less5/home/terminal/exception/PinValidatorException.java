package less5.home.terminal.exception;

public class PinValidatorException extends RuntimeException {
    public PinValidatorException() {
    }

    public PinValidatorException(String message) {
        super(message);
    }

    public PinValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinValidatorException(Throwable cause) {
        super(cause);
    }

    public PinValidatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
