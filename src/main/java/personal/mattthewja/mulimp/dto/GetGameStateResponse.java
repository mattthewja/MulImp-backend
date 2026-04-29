package personal.mattthewja.mulimp.dto;

import lombok.Getter;
import personal.mattthewja.mulimp.model.GameState;

@Getter
public class GetGameStateResponse {
    private final GameState gameState;

    public GetGameStateResponse(GameState gameState) {
        this.gameState = gameState;
    }
}
