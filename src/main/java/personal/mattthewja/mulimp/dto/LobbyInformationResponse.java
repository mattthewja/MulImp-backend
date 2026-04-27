package personal.mattthewja.mulimp.dto;

import personal.mattthewja.mulimp.model.Lobby;

import java.util.List;

public class LobbyInformationResponse {
    private final String lobbyID;
    private final List<String> players;

    public LobbyInformationResponse(Lobby lobby) {
        this.lobbyID = lobby.getLobbyID();
        this.players = lobby.getPlayersAsStrings();
    }

    public String getLobbyID() {
        return lobbyID;
    }

    public List<String> getPlayers() {
        return players;
    }
}
