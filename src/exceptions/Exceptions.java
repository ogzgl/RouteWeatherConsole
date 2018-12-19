package exceptions;

/*
 * Exceptions that can be thrown in program.
 * */
public class Exceptions {
    public static Throwable BadRequestException;
    public static Throwable NotFoundLocation;
    public static Throwable ConnectionError;

    public static class ConnectionError extends Exception { //Connection failure handling.
        public ConnectionError(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class NotFoundLocation extends Exception { //Can be thrown if locations are not found in Maps.
        public NotFoundLocation(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class BadRequestException extends Exception { // Can be thrown if DarkSky cant provide information.
        public BadRequestException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
