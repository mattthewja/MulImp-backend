package personal.mattthewja.mulimp.store;

import org.springframework.stereotype.Component;
import personal.mattthewja.mulimp.model.Lobby;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LobbyStore {
    Map<String, Lobby> lobbies = new ConcurrentHashMap<>();

    public Lobby createLobby(String creator_name) {
        Lobby lobby = new Lobby(creator_name);
        lobbies.put(lobby.getLobbyID(), lobby);
        return lobby;
    }
}
