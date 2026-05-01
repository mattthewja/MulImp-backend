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

//    @PostConstruct
//    @Deprecated
//    public void initDevData() {
//        Lobby devLobby = new Lobby(true,"DevCreator");
//        lobbies.putIfAbsent(devLobby.getLobbyId(), devLobby);
//    }

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

    public Lobby getLobbyWithID(String lobbyId) {
        return lobbies.get(lobbyId);
    }

    public Lobby getLobbyOrThrow(String lobbyId) {
        Lobby lobby = lobbies.get(lobbyId);
        if (lobby == null) {
            throw new LobbyNotFoundException(lobbyId);
        }
        return lobby;
    }

    public void cleanupInactiveLobbies() {
        for (var entry : lobbies.entrySet()) {
            Lobby lobby = entry.getValue();
            synchronized (lobby) {
                lobby.removeInactivePlayers();
                if (lobby.isEmpty()) {
                    lobbies.remove(entry.getKey());
                    System.out.println("Cleanup: " + lobby.getLobbyId());
                }
            }
        }
    }
}
