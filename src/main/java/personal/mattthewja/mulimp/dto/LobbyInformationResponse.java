package personal.mattthewja.mulimp.dto;

import lombok.Getter;
import personal.mattthewja.mulimp.model.Lobby;

import java.util.List;

@Getter
public class LobbyInformationResponse {
    private final String lobbyId;
    private final List<String> players;

    public LobbyInformationResponse(Lobby lobby) {
        this.lobbyId = lobby.getLobbyId();
        this.players = lobby.getPlayersAsStrings();
    }

}
