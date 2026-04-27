package personal.mattthewja.mulimp.service;

import org.springframework.stereotype.Service;
import personal.mattthewja.mulimp.dto.CreateLobbyResponse;
import personal.mattthewja.mulimp.dto.JoinLobbyResponse;
import personal.mattthewja.mulimp.dto.LeaveLobbyResponse;
import personal.mattthewja.mulimp.dto.LobbyInformationResponse;
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

    public JoinLobbyResponse joinPlayerToLobby(String lobbyID, String username) {
        Player player = new Player(username);
        Lobby lobby = lobbyStore.getLobbyWithID(lobbyID);

        if (lobby == null) {
            throw new LobbyNotFoundException(lobbyID);
        }

        synchronized (lobby) {
            if (lobby.isFull()) {
                throw new LobbyFullException(lobbyID);
            }
            if (lobby.hasPlayerNamed(username)) {
                throw new DuplicatePlayerNameException(username);
            }
            lobby.addPlayerToLobby(player);
        }

        return new JoinLobbyResponse(player, lobby);
    }

    public LeaveLobbyResponse leavePlayerFromLobby(String lobbyID, String playerID) {

        Lobby lobby = lobbyStore.getLobbyWithID(lobbyID);
        if (lobby == null) {
            throw new LobbyNotFoundException(lobbyID);
        }

        Player leaving_player = lobby.getPlayerWithId(playerID);
        if (leaving_player == null) {
            throw new BadRequestException("Player with such an ID was not found in lobby");
        }

        lobby.removePlayerFromLobby(leaving_player);

        return new LeaveLobbyResponse(true);
    }

    public LobbyInformationResponse getLobbyInfo(String lobbyID) {
        Lobby lobby = lobbyStore.getLobbyWithID(lobbyID);
        if (lobby == null) {
            throw new LobbyNotFoundException(lobbyID);
        }

        return new LobbyInformationResponse(lobby);
    }
}
