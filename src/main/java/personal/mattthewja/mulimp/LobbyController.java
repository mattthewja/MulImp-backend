package personal.mattthewja.mulimp;

import org.springframework.web.bind.annotation.*;
import personal.mattthewja.mulimp.dto.CreateRequest;
import personal.mattthewja.mulimp.dto.CreateResponse;
import personal.mattthewja.mulimp.lobby.Lobby;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/lobby")
public class LobbyController {
    private final Map<String, Lobby> lobbies = new ConcurrentHashMap<>();

    @PostMapping("/create")
    public CreateResponse createLobby(
            @RequestBody CreateRequest request
    ) {
        final String creator_name = request.getUsername();

        // create lobby
        Lobby lobby = new Lobby(creator_name);
        lobbies.put(lobby.getLobbyID(), lobby);

        return new CreateResponse(lobby);
    }

    /*
    @PostMapping("/{lobbyID}/delete")
    public DeleteResponse deleteLobby(
            @PathVariable String lobbyID,
            @RequestBody DeleteRequest request
    ) {

        throw new UnsupportedOperationException();

    }

    @PostMapping("/{lobbyID}/join")
    public JoinResponse joinLobby(
            @PathVariable String lobbyID,
            @RequestBody JoinRequest request
    ) {

        throw new UnsupportedOperationException();

    }

    @PostMapping("/{lobbyID}/leave")
    public LeaveResponse leaveLobby(
            @PathVariable String lobbyID,
            @RequestBody LeaveRequest request
    ) {

        throw new UnsupportedOperationException();

    }
     */
}