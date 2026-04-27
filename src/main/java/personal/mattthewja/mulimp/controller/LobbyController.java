package personal.mattthewja.mulimp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.mattthewja.mulimp.dto.*;
import personal.mattthewja.mulimp.service.LobbyService;

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

    @PostMapping("/{lobbyId}/players")
    public ResponseEntity<JoinLobbyResponse> joinLobby(
            @PathVariable String lobbyId,
            @Valid @RequestBody JoinLobbyRequest request
    ) {
        JoinLobbyResponse response = lobbyService.joinPlayerToLobby(lobbyId, request.getUsername());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{lobbyId}/players/{playerId}")
    public ResponseEntity<Void> leaveLobby(
            @PathVariable String lobbyId,
            @PathVariable String playerId
    ) {
        LeaveLobbyResponse response = lobbyService.leavePlayerFromLobby(lobbyId, playerId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{lobbyId}")
    public ResponseEntity<LobbyInformationResponse> getLobbyInfo(
            @PathVariable String lobbyId
    ) {
        LobbyInformationResponse response = lobbyService.getLobbyInfo(lobbyId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}