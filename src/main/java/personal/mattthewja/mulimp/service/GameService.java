package personal.mattthewja.mulimp.service;

import org.springframework.stereotype.Service;
import personal.mattthewja.mulimp.dto.*;
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
        synchronized (lobby) {
            lobby.startGame();
            return new StartGameResponse(true);
        }
    }

    public GetGameStateResponse getGameState(String lobbyId) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);
        synchronized (lobby) {
            Game game = lobby.getGame();
            game.advanceGameState();
            return new GetGameStateResponse(lobby);
        }
    }

    public GetPlayerStateResponse getPlayerState(String lobbyId, String playerId) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);

        synchronized (lobby) {
            Game game = lobby.getGame();
            game.advanceGameState();
            Player player = lobby.getPlayerWithIdOrThrow(playerId);

            GameState gameState = game.getGameState();
            String question = game.getQuestionFor(player);

            return new GetPlayerStateResponse(gameState, question,
                    game.hasPlayerAnswered(player),
                    game.hasPlayerVoted(player)
            );
        }
    }

    public PostPlayerAnswerResponse submitAnswer(String lobbyId, String playerId, String answer) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);

        synchronized (lobby) {
            Game game = lobby.getGame();
            game.advanceGameState();
            Player player = lobby.getPlayerWithIdOrThrow(playerId);

            game.submitAnswer(player, answer);
            return new PostPlayerAnswerResponse(true);
        }
    }

    public PostPlayerVoteResponse submitVote(String lobbyId, String playerId, String vote) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);

        synchronized (lobby) {
            Game game = lobby.getGame();
            game.advanceGameState();
            Player voter = lobby.getPlayerWithIdOrThrow(playerId);
            Player voted = lobby.getPlayerNamedOrThrow(vote);

            game.submitVote(voter, voted);
            return new PostPlayerVoteResponse(true);
        }
    }

}
