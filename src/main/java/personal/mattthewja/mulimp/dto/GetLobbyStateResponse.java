package personal.mattthewja.mulimp.dto;

import lombok.Getter;
import personal.mattthewja.mulimp.model.LobbyState;

@Getter
public class GetLobbyStateResponse {
    private final LobbyState lobbyState;

    public GetLobbyStateResponse(LobbyState lobbyState) {
        this .lobbyState = lobbyState;
    }
}
