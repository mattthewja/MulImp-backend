package personal.mattthewja.mulimp.service;

import org.springframework.stereotype.Service;
import personal.mattthewja.mulimp.dto.CreateLobbyRequest;
import personal.mattthewja.mulimp.dto.CreateLobbyResponse;
import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.store.LobbyStore;
import personal.mattthewja.mulimp.store.exception.BadRequestException;
import personal.mattthewja.mulimp.store.exception.InternalLogicException;

@Service
public class LobbyService {
    private final LobbyStore lobbyStore;

    public LobbyService(LobbyStore lobbyStore) {
        this.lobbyStore = lobbyStore;
    }

    public CreateLobbyResponse createLobby(CreateLobbyRequest request) {
        // validate request
        String creator_name = request.getUsername();

        if (creator_name.isBlank()) {
            // throw domain exception and use rest controller advice to handle
            throw new BadRequestException("creator name cannot be blank");
        }

        // ask store to create and return a lobby
        Lobby lobby = lobbyStore.createLobby(creator_name);

        if (lobby == null) {
            // throw failure
            throw new InternalLogicException("failure to create lobby");
        }

        return new CreateLobbyResponse(lobby);
    }
}
