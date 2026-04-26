package personal.mattthewja.mulimp.service;

import org.springframework.stereotype.Service;
import personal.mattthewja.mulimp.dto.CreateLobbyResponse;
import personal.mattthewja.mulimp.dto.JoinLobbyResponse;
import personal.mattthewja.mulimp.exception.BadRequestException;
import personal.mattthewja.mulimp.exception.NotYetImplementedException;
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
        if (username.isBlank()) {
            throw new BadRequestException("Username cannot be blank");
        }
        if (username.length() > 20) {
            throw new BadRequestException("Username cannot be greater than 20 characters long");
        }

        Player creator = new Player(username);
        Lobby lobby = lobbyStore.createLobby(creator);

        return new CreateLobbyResponse(creator, lobby);
    }

    public JoinLobbyResponse joinPlayerToLobby(String lobbyID, String username) {

        throw new NotYetImplementedException();

    }

}
