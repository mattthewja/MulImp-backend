package personal.mattthewja.mulimp.service;

import org.springframework.stereotype.Service;
import personal.mattthewja.mulimp.dto.*;
import personal.mattthewja.mulimp.model.*;
import personal.mattthewja.mulimp.store.LobbyStore;
import personal.mattthewja.mulimp.store.QuestionStore;

import java.time.Instant;

@Service
public class GameService {
    private final LobbyStore lobbyStore;
    private final QuestionStore questionStore;

    public GameService(LobbyStore lobbyStore, QuestionStore questionStore) {
        this.lobbyStore = lobbyStore;
        this.questionStore = questionStore;
    }

    public StartGameResponse startGame(String lobbyId) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);
        synchronized (lobby) {
            QuestionPair questions = questionStore.getRandomQuestionPair();
            lobby.startGame(questions.getQ1(), questions.getQ2());
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
            player.markActive();
//            System.out.println(player.getPlayerId() + ":" + player.getName());

            GameState gameState = game.getGameState();
            String question = gameState != GameState.DISCUSSION ?
                    game.getQuestionFor(player) : game.getRealQuestion();
//            System.out.println("has answered?: " + game.hasPlayerAnswered(player));
//            System.out.println("has voted?: " + game.hasPlayerVoted(player));

            return new GetPlayerStateResponse(gameState, question,
                    game.hasPlayerAnswered(player),
                    game.hasPlayerVoted(player),
                    lobby.ownerIs(player)
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
            game.advanceGameState();
            System.out.println(player.getName() + " answered: " + answer);
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
            game.advanceGameState();
            System.out.println(voter.getName() + " answered: " + voted.getName());
            return new PostPlayerVoteResponse(true);
        }
    }
}
