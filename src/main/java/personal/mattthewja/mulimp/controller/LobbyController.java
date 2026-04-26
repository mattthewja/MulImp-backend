package personal.mattthewja.mulimp.controller;

import org.springframework.web.bind.annotation.*;
import personal.mattthewja.mulimp.dto.CreateLobbyRequest;
import personal.mattthewja.mulimp.dto.CreateLobbyResponse;
import personal.mattthewja.mulimp.service.LobbyService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/lobby")
public class LobbyController {

    private final LobbyService lobbyService;

    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @PostMapping("/create")
    public CreateLobbyResponse createLobby(
            @RequestBody CreateLobbyRequest request
    ) {
        return lobbyService.createLobby(request);
    }

//    @PostMapping("/{lobbyID}/delete")
//    public DeleteResponse deleteLobby(
//            @PathVariable String lobbyID,
//            @RequestBody DeleteRequest request
//    ) {
//
//        throw new UnsupportedOperationException();
//
//    }

//    @PostMapping("/{lobbyID}/join")
//    public JoinResponse joinLobby(
//            @PathVariable String lobbyID,
//            @RequestBody JoinRequest request
//    ) {
//        String joiner_name = request.getUsername();
//
//
//
//        throw new UnsupportedOperationException();
//
//    }

//    @PostMapping("/{lobbyID}/leave")
//    public LeaveResponse leaveLobby(
//            @PathVariable String lobbyID,
//            @RequestBody LeaveRequest request
//    ) {
//
//        throw new UnsupportedOperationException();
//
//    }
}