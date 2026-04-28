package personal.mattthewja.mulimp.store;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import personal.mattthewja.mulimp.exception.*;
import personal.mattthewja.mulimp.model.Lobby;
import personal.mattthewja.mulimp.model.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class LobbyStore {
    Map<String, Lobby> lobbies = new ConcurrentHashMap<>();

    @PostConstruct
    public void initDevData() {
        Lobby devLobby = new Lobby(true,"DevCreator");
        lobbies.putIfAbsent(devLobby.getLobbyId(), devLobby);
    }

    public Lobby createLobby(Player creator) {
        Lobby lobby = new Lobby(creator);
        addLobbyToLobbies(lobby);
        return lobby;
    }

    @SuppressWarnings("all")
    public Lobby removeLobby(Lobby lobby) {
        return lobbies.remove(lobby.getLobbyId());
    }

    public void addLobbyToLobbies(Lobby lobby) {
        Lobby previous = lobbies.putIfAbsent(lobby.getLobbyId(), lobby);
        if (previous != null) {
            throw new InternalLogicException("UUID collision");
        }
    }

    public Lobby getLobbyWithID(String lobbyID) {
        return lobbies.get(lobbyID);
    }
}
