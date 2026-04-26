package personal.mattthewja.mulimp.dto;

import personal.mattthewja.mulimp.exception.NotYetImplementedException;
import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.model.Player;

import java.util.ArrayList;
import java.util.List;

public class CreateLobbyResponse {
    private final String lobbyID;
    private final String userID;
    private final List<String> players;

    public CreateLobbyResponse(Player creator, Lobby lobby) {
        this.lobbyID = lobby.getLobbyID();
        this.userID = creator.getPlayerID();
        this.players = lobby.getPlayersAsStrings();
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
