package personal.mattthewja.mulimp.service;

import org.springframework.stereotype.Service;
import personal.mattthewja.mulimp.dto.StartGameResponse;
import personal.mattthewja.mulimp.exception.LobbyNotFoundException;
import personal.mattthewja.mulimp.exception.NotYetImplementedException;
import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.store.LobbyStore;

@Service
public class GameService {
    private final LobbyStore lobbyStore;

    public GameService(LobbyStore lobbyStore) {
        this.lobbyStore = lobbyStore;
    }

    public StartGameResponse startGame(String lobbyId) {
        Lobby lobby = lobbyStore.getLobbyWithID(lobbyId);
        if (lobby == null) {
            throw new LobbyNotFoundException(lobbyId);
        }

        lobby.startGame();
        return new StartGameResponse(true);
    }

}
