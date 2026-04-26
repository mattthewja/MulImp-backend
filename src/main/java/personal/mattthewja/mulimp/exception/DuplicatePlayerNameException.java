package personal.mattthewja.mulimp.exception;

public class DuplicatePlayerNameException extends RuntimeException {
    public DuplicatePlayerNameException(String message) {
        super("duplicate player name: " + message);
    }
}
