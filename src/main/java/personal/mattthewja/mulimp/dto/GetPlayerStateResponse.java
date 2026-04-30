package personal.mattthewja.mulimp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import personal.mattthewja.mulimp.model.GameState;

@Getter
@AllArgsConstructor
public class GetPlayerStateResponse {
    private final GameState gameState;
    private final String question;
    private final boolean hasAnswered;
    private final boolean hasVoted;
}
