package personal.mattthewja.mulimp.model;

import lombok.Getter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
public class Game {
    private static final int ANSWERING_DURATION = 20;
    private static final int DISCUSSION_DURATION = 180;

    private Player imposterPlayer;
    private final Map<Player, String> playerAnswers = new HashMap<>();
    private final Map<Player, Player> playerVotes = new HashMap<>();

    private String realQuestion;
    private String imposterQuestion;
    private Instant phaseEndsAt;

    private GameState gameState = GameState.IN_LOBBY;

    public void startGame(List<Player> players, String realQuestion, String imposterQuestion) {
        this.imposterPlayer = players.get((int) Math.floor(Math.random() * players.size()));
        this.realQuestion = realQuestion;
        this.imposterQuestion = imposterQuestion;

        for (Player player : players) {
            playerAnswers.put(player, "");
            playerVotes.put(player, player); // default self vote on no vote
        }

        phaseEndsAt = Instant.now().plus(ANSWERING_DURATION, ChronoUnit.SECONDS);
        gameState = GameState.ANSWERING;
    }

    public boolean hasPlayerAnswered(Player player) {
        return !Objects.equals(playerAnswers.get(player), "");
    }

    public String getAnswerOfPlayer(Player player) {
        return playerAnswers.get(player);
    }

    public void setAnswerOfPlayer(Player player, String answer) {
        playerAnswers.put(player, answer);
    }

    public Player getVoteOfPlayer(Player player) {
        return playerVotes.get(player);
    }

    public void setVoteOfPlayer(Player voter, Player voted) {
        playerVotes.put(voter, voted);
    }

    public void advanceGameState() {
        if (Instant.now().isAfter(phaseEndsAt)) {
            if (gameState == GameState.ANSWERING) {
                gameState = GameState.DISCUSSION;
                phaseEndsAt.plus(DISCUSSION_DURATION, ChronoUnit.SECONDS);
            } else if (gameState == GameState.DISCUSSION) {
                gameState = GameState.IN_LOBBY;
                phaseEndsAt.plus(9999, ChronoUnit.HOURS);
            }
        }
    }

    public boolean isGameDone() {
        return gameState == GameState.IN_LOBBY;
    }

    public String getQuestionFor(Player player) {
        return player.equals(imposterPlayer) ? imposterQuestion : realQuestion;
    }
}
