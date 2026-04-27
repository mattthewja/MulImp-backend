package personal.mattthewja.mulimp.dto;

import lombok.Getter;
import personal.mattthewja.mulimp.model.Lobby;

import java.util.List;

@Getter
public class GetLobbyResponse {
    private final String lobbyId;
    private final List<String> players;

    public GetLobbyResponse(Lobby lobby) {
        this.lobbyId = lobby.getLobbyId();
        this.players = lobby.getPlayersAsStrings();
    }

}
