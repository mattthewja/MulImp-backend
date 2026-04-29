package personal.mattthewja.mulimp.service;

import org.springframework.stereotype.Service;
import personal.mattthewja.mulimp.dto.GetGameStateResponse;
import personal.mattthewja.mulimp.dto.GetPlayerStateResponse;
import personal.mattthewja.mulimp.dto.StartGameResponse;
import personal.mattthewja.mulimp.exception.GameHasNotStartedException;
import personal.mattthewja.mulimp.exception.LobbyNotFoundException;
import personal.mattthewja.mulimp.exception.PlayerNotFoundException;
import personal.mattthewja.mulimp.model.Game;
import personal.mattthewja.mulimp.model.GameState;
import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.model.Player;
import personal.mattthewja.mulimp.store.LobbyStore;

@Service
public class GameService {
    private final LobbyStore lobbyStore;

    public GameService(LobbyStore lobbyStore) {
        this.lobbyStore = lobbyStore;
    }

    public StartGameResponse startGame(String lobbyId) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);

        lobby.startGame();
        return new StartGameResponse(true);
    }

    public GetGameStateResponse getGameState(String lobbyId) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);
        Game game = lobby.getGame();

        return new GetGameStateResponse(game.getGameState());
    }

    public GetPlayerStateResponse getPlayerState(String lobbyId, String playerId) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);

        Player player = lobby.getPlayerWithId(playerId);
        if (player == null) {
            throw new PlayerNotFoundException(playerId);
        }
        Game game = lobby.getGame();

        GameState gameState = game.getGameState();
        String question = game.getQuestionFor(player);

        return new GetPlayerStateResponse(gameState, question, game.hasPlayerAnswered(player));
    }
}
