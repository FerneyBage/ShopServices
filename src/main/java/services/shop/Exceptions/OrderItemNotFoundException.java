package services.shop.Exceptions;

public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException() {
    }

    public OrderItemNotFoundException(String message) {
        super(message);
    }

    public OrderItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderItemNotFoundException(Throwable cause) {
        super(cause);
    }

    public OrderItemNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
