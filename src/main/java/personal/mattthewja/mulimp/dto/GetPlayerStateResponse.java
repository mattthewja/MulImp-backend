package personal.mattthewja.mulimp.dto;

import lombok.Getter;
import personal.mattthewja.mulimp.model.GameState;

@Getter
public class GetPlayerStateResponse {
    private final GameState gameState;
    private final String question;
    private final boolean hasAnswered;

    public GetPlayerStateResponse(GameState gameState, String question, boolean hasAnswered) {
        this.gameState = gameState;
        this.question = question;
        this.hasAnswered = hasAnswered;
    }
}
