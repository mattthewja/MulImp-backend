package personal.mattthewja.mulimp.exception;

public class LobbyFullException extends RuntimeException {
    public LobbyFullException(String message) {
        super("lobby is full: " + message);
    }
}
