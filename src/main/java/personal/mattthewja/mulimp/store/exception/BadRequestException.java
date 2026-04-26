package personal.mattthewja.mulimp.store.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super("Bad Request Exception: " + message);
    }
}
