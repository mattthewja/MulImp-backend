package personal.mattthewja.mulimp.model;

import lombok.Getter;
import personal.mattthewja.mulimp.exception.BadRequestException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Game {
    // dev testing, these should be 30, 180, and 10 respectively
    private static final int ANSWERING_DURATION = 30;
    private static final int DISCUSSION_DURATION = 180;
    private static final int RESULTS_DURATION = 10;

    private List<Player> players;
    private Player imposterPlayer;
    private final Map<Player, String> playerAnswers = new HashMap<>();
    private final Map<Player, Player> playerVotes = new HashMap<>();

    private String realQuestion;
    private String imposterQuestion;
    private Instant phaseEndsAt;

    private GameState gameState = GameState.IN_LOBBY;

    public void startGame(List<Player> players, String realQuestion, String imposterQuestion) {
        this.players = players;
        this.imposterPlayer = players.get((int) Math.floor(Math.random() * players.size()));
        this.realQuestion = realQuestion;
        this.imposterQuestion = imposterQuestion;

        this.playerAnswers.clear();
        this.playerVotes.clear();
//        for (Player player : players) {
//            playerAnswers.put(player, "");
//            playerVotes.put(player, player); // default self vote on no vote
//        }

        phaseEndsAt = Instant.now().plus(ANSWERING_DURATION, ChronoUnit.SECONDS);
        gameState = GameState.ANSWERING;
    }

    public boolean hasPlayerAnswered(Player player) {
        return playerAnswers.containsKey(player);
    }

    public boolean hasPlayerVoted(Player player) {
        return playerVotes.containsKey(player);
    }

    public void submitAnswer(Player player, String answer) {
        if (gameState != GameState.ANSWERING) {
            throw new BadRequestException("Invalid Game State");
        }

        if (playerAnswers.containsKey(player)) {
            throw new BadRequestException("Already answered");
        }

        playerAnswers.put(player, answer);
    }

    public void submitVote(Player player, Player vote) {
        if (gameState != GameState.DISCUSSION) {
            throw new BadRequestException("Invalid Game State");
        }

        if (playerVotes.containsKey(player)) {
            throw new BadRequestException("Player already voted");
        }

        playerVotes.put(player, vote);
    }

    public void advanceGameState() {
        if (isInLobby()) return;

        if (isTimeUp() || allPlayersDone()) {
            switch (gameState) {
                case ANSWERING -> moveToDiscussion();
                case DISCUSSION -> moveToResults();
                case RESULTS -> returnToLobby();
            }
        }
    }

    public boolean isInLobby() {
        return gameState == GameState.IN_LOBBY;
    }

    public String getQuestionFor(Player player) {
        return player.equals(imposterPlayer) ? imposterQuestion : realQuestion;
    }

    private boolean allPlayersDone() {
        if (gameState == GameState.ANSWERING) {
            boolean value = players.size() == playerAnswers.size();
            return players.size() == playerAnswers.size();
        } else if (gameState == GameState.DISCUSSION) {
            boolean value = players.size() == playerVotes.size();
            return players.size() == playerVotes.size();
        }

        return false;
    }

    private boolean isTimeUp() {
        if (gameState == GameState.IN_LOBBY) {
            return false;
        }

        return Instant.now().isAfter(phaseEndsAt);
    }

    private void moveToDiscussion() {
        gameState = GameState.DISCUSSION;
        phaseEndsAt = phaseEndsAt.plusSeconds(DISCUSSION_DURATION);
    }

    private void moveToResults() {
        gameState = GameState.RESULTS;
        phaseEndsAt = phaseEndsAt.plusSeconds(RESULTS_DURATION);
    }

    private void returnToLobby() {
        gameState = GameState.IN_LOBBY;
    }

    public Player getMostVoted() {
        if (gameState != GameState.RESULTS || playerVotes.isEmpty()) return null;

        Map<Player, Integer> voteCounts = new HashMap<>();

        for (Player votedPlayer : playerVotes.values()) {
            voteCounts.put(
                    votedPlayer,
                    voteCounts.getOrDefault(votedPlayer, 0) + 1
            );
        }

        Player mostVoted = null;
        int maxVotes = 0;

        for (var entry : voteCounts.entrySet()) {
            if (entry.getValue() > maxVotes) {
                mostVoted = entry.getKey();
                maxVotes = entry.getValue();
            } else if (entry.getValue() == maxVotes) {
                // do nothing for now, but actually need
                // tie logic
            }
        }

        return mostVoted;
    }
}
