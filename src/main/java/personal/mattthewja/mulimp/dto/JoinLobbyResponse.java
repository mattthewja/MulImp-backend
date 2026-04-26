package personal.mattthewja.mulimp.dto;

import personal.mattthewja.mulimp.exception.NotYetImplementedException;
import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.model.Player;

import java.util.ArrayList;
import java.util.List;

public class JoinLobbyResponse {
    private final String lobbyID;
    private final List<String> players;
    private final String userID;

    public JoinLobbyResponse(Player player, Lobby lobby) {
        this.lobbyID = lobby.getLobbyID();
        this.players = lobby.getPlayersAsStrings();
        this.userID = player.getPlayerID();
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public List<String> getPlayers() {
        return players;
    }

    public String getUserID() {
        return userID;
    }
}
