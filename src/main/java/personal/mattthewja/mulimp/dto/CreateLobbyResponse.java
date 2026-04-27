package personal.mattthewja.mulimp.dto;

import lombok.Getter;
import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.model.Player;

import java.util.List;

@Getter
public class CreateLobbyResponse {
    private final String lobbyId;
    private final String playerId;
    private final List<String> players;

    public CreateLobbyResponse(Player creator, Lobby lobby) {
        this.lobbyId = lobby.getLobbyId();
        this.playerId = creator.getPlayerId();
        this.players = lobby.getPlayersAsStrings();
    }
}
