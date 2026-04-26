package personal.mattthewja.mulimp.store.exception;

public class InternalLogicException extends RuntimeException {
    public InternalLogicException(String data) {
        super("Internal Logic Error: " + data);
    }
}
