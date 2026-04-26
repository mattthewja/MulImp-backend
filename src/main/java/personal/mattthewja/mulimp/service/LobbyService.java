package personal.mattthewja.mulimp.service;

import org.springframework.stereotype.Service;
import personal.mattthewja.mulimp.dto.CreateLobbyResponse;
import personal.mattthewja.mulimp.dto.JoinLobbyResponse;
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

    public void validateUsername(String username) {
        if (username.isBlank()) {
            throw new BadRequestException("Username cannot be blank");
        }
        if (username.length() > 20) {
            throw new BadRequestException("Username cannot be greater than 20 characters long");
        }
    }

    public CreateLobbyResponse createLobby(String username) {
        validateUsername(username);

        Player creator = new Player(username);
        Lobby lobby = lobbyStore.createLobby(creator);

        return new CreateLobbyResponse(creator, lobby);
    }

    public JoinLobbyResponse joinPlayerToLobby(String lobbyID, String username) {
        validateUsername(username);

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

}
