package personal.mattthewja.mulimp.exception;

public class GameHasStartedException extends RuntimeException {
    public GameHasStartedException() {
        super("Game has already started");
    }
}
