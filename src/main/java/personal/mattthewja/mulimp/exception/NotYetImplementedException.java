package personal.mattthewja.mulimp.exception;

/**
 * All credit for this exception and implementation goes to
 * Dennis Cosgrove at Washington University in St. Louis
 * He is a great professor and most importantly a kind soul!
 */
public class NotYetImplementedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotYetImplementedException() {
        super(Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    public NotYetImplementedException(String detail) {
        super(detail);
    }

}