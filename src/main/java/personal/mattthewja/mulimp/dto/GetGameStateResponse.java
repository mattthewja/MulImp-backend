package personal.mattthewja.mulimp.dto;

import lombok.Getter;
import personal.mattthewja.mulimp.model.Game;
import personal.mattthewja.mulimp.model.GameState;
import personal.mattthewja.mulimp.model.Lobby;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class GetGameStateResponse {
    private final GameState gameState;
    private final List<String> players;
    private final List<PlayerAnswerPair> answers;

    public GetGameStateResponse(Lobby lobby) {
        Game game = lobby.getGame();

        this.gameState = game.getGameState();
        this.players = lobby.getPlayersAsStrings();

        answers = new ArrayList<>();
        if (gameState == GameState.DISCUSSION || gameState == GameState.RESULTS) {
            for (var a : game.getPlayerAnswers().entrySet()) {
                answers.add(new PlayerAnswerPair(a.getKey().getName(), a.getValue()));
            }
        }
    }
}
