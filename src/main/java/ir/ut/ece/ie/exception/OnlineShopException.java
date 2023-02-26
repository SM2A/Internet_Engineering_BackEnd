package ir.ut.ece.ie.exception;

public class OnlineShopException extends RuntimeException {
    public OnlineShopException() {
        super();
    }

    public OnlineShopException(String message) {
        super(message);
    }

    public OnlineShopException(Throwable cause) {
        super(cause);
    }

    public OnlineShopException(String message, Throwable cause) {
        super(message, cause);
    }

}
