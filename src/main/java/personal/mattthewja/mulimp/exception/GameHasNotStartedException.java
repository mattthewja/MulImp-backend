package personal.mattthewja.mulimp.exception;

public class GameHasNotStartedException extends RuntimeException {
    public GameHasNotStartedException() {
        super("Game has not started");
    }
}
