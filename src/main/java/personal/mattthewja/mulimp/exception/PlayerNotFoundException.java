package personal.mattthewja.mulimp.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException() {
        super("Player not found");
    }
}
