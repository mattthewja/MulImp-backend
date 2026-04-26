package personal.mattthewja.mulimp.dto;

import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.model.Player;

import java.util.ArrayList;
import java.util.List;

public class CreateLobbyResponse {
    private final String lobbyID;
    private final String userID;
    private final List<String> players = new ArrayList<>();

    public CreateLobbyResponse(Lobby lobby) {
        this.lobbyID = lobby.getLobbyID();
        this.userID = lobby.getOwnerID();
        for (Player player : lobby.getPlayers()) {
            this.players.add(player.getName());
        }
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
