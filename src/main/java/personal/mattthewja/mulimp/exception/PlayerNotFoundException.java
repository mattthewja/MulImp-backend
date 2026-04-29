package personal.mattthewja.mulimp.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String message) {
        super("Player not found: " + message);
    }
}
