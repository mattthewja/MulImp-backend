package personal.mattthewja.mulimp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.mattthewja.mulimp.dto.GetGameStateResponse;
import personal.mattthewja.mulimp.dto.GetPlayerStateResponse;
import personal.mattthewja.mulimp.dto.GetStateResponse;
import personal.mattthewja.mulimp.dto.StartGameResponse;
import personal.mattthewja.mulimp.exception.NotYetImplementedException;
import personal.mattthewja.mulimp.service.GameService;
import personal.mattthewja.mulimp.service.LobbyService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/lobby/{lobbyId}/game")
public class GameController {

    private final GameService gameService;
    private final LobbyService lobbyService;

    public GameController(GameService gameService, LobbyService lobbyService) { this.gameService = gameService;
        this.lobbyService = lobbyService;
    }

    @PostMapping("/start")
    public ResponseEntity<StartGameResponse> startGame(
            @PathVariable String lobbyId
    ) {
        StartGameResponse response = gameService.startGame(lobbyId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/state")
    public ResponseEntity<GetGameStateResponse> getState(
            @PathVariable String lobbyId
    ) {
        GetGameStateResponse response = gameService.getGameState(lobbyId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/players/{playerId}/state")
    public ResponseEntity<GetPlayerStateResponse> getPlayerState(
            @PathVariable String lobbyId,
            @PathVariable String playerId
    ) {
        GetPlayerStateResponse response = gameService.getPlayerState(lobbyId, playerId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


}
