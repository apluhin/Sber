package less5.home.terminal.exception;

public class TerminalException extends RuntimeException {

    public TerminalException() {
    }

    public TerminalException(String message) {
        super(message);
    }

    public TerminalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TerminalException(Throwable cause) {
        super(cause);
    }

    public TerminalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
