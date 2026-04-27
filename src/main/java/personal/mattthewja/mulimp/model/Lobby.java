package personal.mattthewja.mulimp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lobby {
    private final String lobbyId;
    private Player owner;
    private final List<Player> players;
    private final static int maxLobbySize = 8;

    public Lobby(boolean dev, String creator_name) {
        Player creator = new Player(creator_name);
        this.lobbyId = "0";
        players = new ArrayList<>();
        players.add(creator);
        players.add(new Player("1"));
        players.add(new Player("2"));
        players.add(new Player("3"));
        players.add(new Player("4"));
        players.add(new Player("5"));
        players.add(new Player("6"));
        players.add(new Player("7"));
    }

    public Lobby(String creator_name) {
        Player creator = new Player(creator_name);

        lobbyId = UUID.randomUUID().toString();
        owner = creator;

        players = new ArrayList<>();
        players.add(creator);
    }

    public Lobby(Player creator) {
        lobbyId = UUID.randomUUID().toString();
        owner = creator;

        players = new ArrayList<>();
        players.add(creator);
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player new_owner) {
        this.owner = new_owner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayersAsStrings() {
        List<String> player_names = new ArrayList<>();
        for (Player p : players) {
            player_names.add(p.getName());
        }

        return player_names;
    }

    public Player getPlayerNamed(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public Player getPlayerWithId(String playerID) {
        for (Player p : players) {
            if (p.getPlayerId().equals(playerID)) {
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

    public void addPlayerToLobby(Player player) {
        this.players.add(player);
    }

    public void removePlayerFromLobby(Player player) {
        this.players.remove(player);
    }
}
