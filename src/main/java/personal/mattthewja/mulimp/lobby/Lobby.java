package personal.mattthewja.mulimp.lobby;

import personal.mattthewja.mulimp.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lobby {
    private final String lobbyID;
    private String ownerID;
    private final List<Player> players;

    public Lobby(String creator_name) {
        Player creator = new Player(creator_name);

        lobbyID = UUID.randomUUID().toString();
        ownerID = creator.getPlayerID();

        players = new ArrayList<>();
        players.add(creator);
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
