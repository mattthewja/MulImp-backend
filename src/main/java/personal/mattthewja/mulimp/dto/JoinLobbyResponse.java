package personal.mattthewja.mulimp.dto;

import lombok.Getter;
import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.model.Player;

import java.util.List;

@Getter
public class JoinLobbyResponse {
    private final String lobbyId;
    private final List<String> players;
    private final String playerId;

    public JoinLobbyResponse(Player player, Lobby lobby) {
        this.lobbyId = lobby.getLobbyId();
        this.players = lobby.getPlayersAsStrings();
        this.playerId = player.getPlayerId();
    }

}
