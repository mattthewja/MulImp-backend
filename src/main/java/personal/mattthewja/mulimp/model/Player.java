package personal.mattthewja.mulimp.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Getter
public class Player {
    private static final int INACTIVE_TIMEOUT_TIME = 10;
    private final String playerId;
    private final String name;
    @Setter
    private Instant lastSeen;

    public Player(String name) {
        this.playerId = UUID.randomUUID().toString();
        this.name = name;
        this.lastSeen = Instant.now();
    }

    public boolean isActive() {
        return lastSeen.compareTo(Instant.now().plus(INACTIVE_TIMEOUT_TIME, ChronoUnit.SECONDS)) > 0;
    }
}
