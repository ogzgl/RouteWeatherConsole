package exceptions;

public class Exceptions {
    public static Throwable BadRequestException;
    public static Throwable NotFoundLocation;
    public static Throwable ConnectionError;

    public static class ConnectionError extends Exception {
        public ConnectionError(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class NotFoundLocation extends Exception {
        public NotFoundLocation(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class BadRequestException extends Exception {
        public BadRequestException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
