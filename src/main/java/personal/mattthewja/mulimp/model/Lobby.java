package personal.mattthewja.mulimp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lobby {
    private final String lobbyID;
    private Player owner;
    private final List<Player> players;
    private final static int maxLobbySize = 8;

    public Lobby(boolean dev, String creator_name) {
        Player creator = new Player(creator_name);
        this.lobbyID = "0";
        players = new ArrayList<>();
    }

    public Lobby(String creator_name) {
        Player creator = new Player(creator_name);

        lobbyID = UUID.randomUUID().toString();
        owner = creator;

        players = new ArrayList<>();
        players.add(creator);
    }

    public Lobby(Player creator) {
        lobbyID = UUID.randomUUID().toString();
        owner = creator;

        players = new ArrayList<>();
        players.add(creator);
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public Player getOwner() {
        return owner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerNamed(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public boolean hasPlayerNamed(String name) {
        Player player = getPlayerNamed(name);
        return player != null;
    }

    public boolean isFull() {
        return players.size() >= maxLobbySize;
    }

    public void setOwner(Player newOwner) {
        this.owner = newOwner;
    }
}
