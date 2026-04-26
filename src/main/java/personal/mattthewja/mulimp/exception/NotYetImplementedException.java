package personal.mattthewja.mulimp.exception;

public class NotYetImplementedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotYetImplementedException() {
        super(Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    public NotYetImplementedException(String detail) {
        super(detail);
    }

}