package personal.mattthewja.mulimp.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import personal.mattthewja.mulimp.store.LobbyStore;

@Component
public class LobbyCleanupJob {
    private final LobbyStore lobbyStore;

    public LobbyCleanupJob(LobbyStore lobbyStore) {
        this.lobbyStore = lobbyStore;
    }

    @Scheduled(fixedRate = 30000)
    public void cleanup() {
        lobbyStore.cleanupInactiveLobbies();
    }
}
