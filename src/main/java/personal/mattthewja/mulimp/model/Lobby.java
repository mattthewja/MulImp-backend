package personal.mattthewja.mulimp.model;

import lombok.Getter;
import lombok.Setter;
import personal.mattthewja.mulimp.dto.GetGameStateResponse;
import personal.mattthewja.mulimp.exception.GameHasStartedException;
import personal.mattthewja.mulimp.exception.PlayerNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// holy crap is lombok incredible...
// what in the -boilerplate
@Getter
public class Lobby {
    private final String lobbyId;
    @Setter
    private Player owner;
    private final List<Player> players;
    private final static int MAX_LOBBY_SIZE = 8;
    private LobbyState lobbyState = LobbyState.IN_LOBBY;

    private final Game game = new Game();

//    @Deprecated
//    public Lobby(boolean dev, String creator_name) {
//        Player creator = new Player(creator_name);
//        this.lobbyId = "0";
//        players = new ArrayList<>();
//        players.add(creator);
//        players.add(new Player("1"));
//        players.add(new Player("2"));
//        players.add(new Player("3"));
//        players.add(new Player("4"));
//        players.add(new Player("5"));
//        players.add(new Player("6"));
//        players.add(new Player("7"));
//    }

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

    public Player getPlayerNamedOrThrow(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }

        throw new PlayerNotFoundException();
    }

    public Player getPlayerWithId(String playerId) {
        for (Player p : players) {
            if (p.getPlayerId().equals(playerId)) {
                return p;
            }
        }

        return null;
    }

    public Player getPlayerWithIdOrThrow(String playerId) {
        Player player = getPlayerWithId(playerId);
        if (player == null) {
            throw new PlayerNotFoundException();
        } else {
            return player;
        }
    }

    public boolean hasPlayerNamed(String name) {
        Player player = getPlayerNamed(name);
        return player != null;
    }

    public boolean isFull() {
        return players.size() >= MAX_LOBBY_SIZE;
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public void addPlayerToLobby(Player player) {
        this.players.add(player);
    }

    @Deprecated
    public void removePlayerFromLobby(Player player) {
        this.players.remove(player);
    }

    public void removePlayerFromLobbyOwnerAware(Player player) {
        this.players.remove(player);
        if (ownerIs(player)) {
            if (this.isEmpty()) return;
            Player new_owner = players.getFirst();
            setOwner(new_owner);
        }
    }

    public void startGame(String real_question, String imposter_question) {
        if (game.getGameState() != GameState.IN_LOBBY) {
            throw new GameHasStartedException();
        }

        game.startGame(this.getPlayers(), real_question, imposter_question);
        lobbyState = LobbyState.IN_GAME;
    }

    public GetGameStateResponse GetGameState() {
        synchronized (this) {
            Game game = this.game;
            game.advanceGameState();

            return new GetGameStateResponse(this);
        }
    }

    public boolean ownerIs(Player player) {
        return player == this.owner;
    }

    public void removeInactivePlayers() {
        List<Player> inactive_players = new ArrayList<>();
        for (Player player : players) {
            if (player.isActive()) continue;
            inactive_players.add(player);
        }
        for (Player player : inactive_players) {
            removePlayerFromLobbyOwnerAware(player);
            System.out.println("Remove inactive " + player.getPlayerId() + ":" + player.getName());
            System.out.println("New Owner " + this.getOwner());
        }
    }
}
