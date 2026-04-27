package personal.mattthewja.mulimp.dto;

import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.model.Player;

import java.util.List;

public class CreateLobbyResponse {
    private final String lobbyID;
    private final String playerID;
    private final List<String> players;

    public CreateLobbyResponse(Player creator, Lobby lobby) {
        this.lobbyID = lobby.getLobbyID();
        this.playerID = creator.getPlayerID();
        this.players = lobby.getPlayersAsStrings();
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public List<String> getPlayers() {
        return players;
    }

    public String getPlayerID() {
        return playerID;
    }
}
