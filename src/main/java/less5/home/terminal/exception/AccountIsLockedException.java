package less5.home.terminal.exception;

public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountIsLockedException(Throwable cause) {
        super(cause);
    }

    public AccountIsLockedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AccountIsLockedException() {
    }

    public AccountIsLockedException(String message) {
        super(message);
    }

}
