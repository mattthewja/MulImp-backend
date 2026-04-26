package personal.mattthewja.mulimp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.mattthewja.mulimp.dto.CreateLobbyRequest;
import personal.mattthewja.mulimp.dto.CreateLobbyResponse;
import personal.mattthewja.mulimp.dto.JoinLobbyRequest;
import personal.mattthewja.mulimp.dto.JoinLobbyResponse;
import personal.mattthewja.mulimp.exception.NotYetImplementedException;
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
    public ResponseEntity<CreateLobbyResponse> createLobby(
            @RequestBody CreateLobbyRequest request
    ) {
        CreateLobbyResponse response = lobbyService.createLobby(request.getUsername());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/{lobbyID}/join")
    public ResponseEntity<JoinLobbyResponse> joinLobby(
            @PathVariable String lobbyID,
            @RequestBody JoinLobbyRequest request
    ) {
        JoinLobbyResponse response = lobbyService.joinPlayerToLobby(lobbyID, request.getUsername());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}