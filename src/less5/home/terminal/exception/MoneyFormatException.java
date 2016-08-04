package less5.home.terminal.exception;

public class MoneyFormatException extends RuntimeException {
    public MoneyFormatException() {
    }

    public MoneyFormatException(String message) {
        super(message);
    }

    public MoneyFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoneyFormatException(Throwable cause) {
        super(cause);
    }

    public MoneyFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
