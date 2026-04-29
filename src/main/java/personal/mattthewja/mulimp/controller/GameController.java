package personal.mattthewja.mulimp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    public GameController(GameService gameService) { this.gameService = gameService; }

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
    public ResponseEntity<GetStateResponse> getState(
            @PathVariable String lobbyId
    ) {

        throw new NotYetImplementedException();

    }

}
