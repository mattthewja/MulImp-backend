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
    private final GameResult gameResult;

    public GetGameStateResponse(Lobby lobby) {
        Game game = lobby.getGame();

        this.gameState = game.getGameState();
        this.players = lobby.getPlayersAsStrings();

        if (gameState == GameState.DISCUSSION || gameState == GameState.RESULTS) {
            answers = new ArrayList<>();
            for (var a : game.getPlayerAnswers().entrySet()) {
                answers.add(new PlayerAnswerPair(a.getKey().getName(), a.getValue()));
            }
        } else {
            answers = null;
        }

        if (gameState == GameState.RESULTS) {
            if (game.getMostVoted() == null) {
                this.gameResult = new GameResult(
                        game.getImposterPlayer().getName(),
                        null
                );
            } else {
                this.gameResult = new GameResult(
                        game.getImposterPlayer().getName(),
                        game.getMostVoted().getName()
                );
            }
        } else {
            this.gameResult = null;
        }
    }
}
