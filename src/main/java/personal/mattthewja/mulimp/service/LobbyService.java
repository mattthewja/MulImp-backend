package personal.mattthewja.mulimp.service;

import org.springframework.stereotype.Service;
import personal.mattthewja.mulimp.dto.*;
import personal.mattthewja.mulimp.exception.*;
import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.model.Player;
import personal.mattthewja.mulimp.store.LobbyStore;

@Service
public class LobbyService {
    private final LobbyStore lobbyStore;

    public LobbyService(LobbyStore lobbyStore) {
        this.lobbyStore = lobbyStore;
    }

    public CreateLobbyResponse createLobby(String username) {
        Player creator = new Player(username);
        Lobby lobby = lobbyStore.createLobby(creator);

        return new CreateLobbyResponse(creator, lobby);
    }

    public JoinLobbyResponse joinPlayerToLobby(String lobbyId, String username) {
        Player player = new Player(username);
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);

        synchronized (lobby) {
            if (lobby.isFull()) {
                throw new LobbyFullException(lobbyId);
            }
            if (lobby.hasPlayerNamed(username)) {
                throw new DuplicatePlayerNameException(username);
            }
            lobby.addPlayerToLobby(player);
        }

        return new JoinLobbyResponse(player, lobby);
    }

    public LeaveLobbyResponse leavePlayerFromLobby(String lobbyId, String playerId) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);

        Player leaving_player = lobby.getPlayerWithId(playerId);
        if (leaving_player == null) {
            throw new BadRequestException("Player with such an ID was not found in lobby");
        }

        lobby.removePlayerFromLobby(leaving_player);

        if (lobby.isEmpty()) {
            lobbyStore.removeLobby(lobby);
            return new LeaveLobbyResponse(true);
        }

        if (leaving_player.equals(lobby.getOwner())) {
            Player next_owner = lobby.getPlayers().getFirst();
            lobby.setOwner(next_owner);
        }

        return new LeaveLobbyResponse(true);
    }

    public GetLobbyResponse getLobbyInfo(String lobbyId) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);

        return new GetLobbyResponse(lobby);
    }

    public GetLobbyStateResponse getLobbyState(String lobbyId) {
        Lobby lobby = lobbyStore.getLobbyOrThrow(lobbyId);

        return new GetLobbyStateResponse(lobby.getLobbyState());
    }
}
