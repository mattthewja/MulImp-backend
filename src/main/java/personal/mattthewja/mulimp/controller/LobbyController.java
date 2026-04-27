package personal.mattthewja.mulimp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.mattthewja.mulimp.dto.*;
import personal.mattthewja.mulimp.exception.NotYetImplementedException;
import personal.mattthewja.mulimp.service.LobbyService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// stopgap fix before CORS is enabled
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/lobby")
public class LobbyController {

    private final LobbyService lobbyService;

    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @PostMapping("")
    public ResponseEntity<CreateLobbyResponse> createLobby(
            @Valid @RequestBody CreateLobbyRequest request
    ) {
        CreateLobbyResponse response = lobbyService.createLobby(request.getUsername());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/{lobbyID}/players")
    public ResponseEntity<JoinLobbyResponse> joinLobby(
            @PathVariable String lobbyID,
            @Valid @RequestBody JoinLobbyRequest request
    ) {
        JoinLobbyResponse response = lobbyService.joinPlayerToLobby(lobbyID, request.getUsername());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{lobbyID}/players/{playerId}")
    public ResponseEntity<Void> leaveLobby(
            @PathVariable String lobbyID,
            @PathVariable String playerId
    ) {
        LeaveLobbyResponse response = lobbyService.leavePlayerFromLobby(lobbyID, playerId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{lobbyID}")
    public ResponseEntity<LobbyInformationResponse> getLobbyInfo(
            @PathVariable String lobbyID
    ) {
        LobbyInformationResponse response = lobbyService.getLobbyInfo(lobbyID);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}