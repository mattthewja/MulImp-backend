package personal.mattthewja.mulimp.player;

import java.util.UUID;

public class Player {
    private final String playerID;
    private final String name;

    public Player(String name) {
        this.playerID = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getPlayerID() {
        return playerID;
    }

    public String getName() {
        return name;
    }
}
