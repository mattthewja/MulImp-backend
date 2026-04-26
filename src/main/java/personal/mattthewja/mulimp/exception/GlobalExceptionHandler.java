package personal.mattthewja.mulimp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import personal.mattthewja.mulimp.dto.DefaultErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<DefaultErrorResponse> handleInternalServerError(InternalLogicException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new DefaultErrorResponse("INTERNAL_ERROR", e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorResponse> handleBadRequestError(BadRequestException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new DefaultErrorResponse("BAD_REQUEST", e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorResponse> handleDuplicatePlayerNameError(DuplicatePlayerNameException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new DefaultErrorResponse("DUPLICATE_NAME", e.getMessage()));

    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorResponse> handleLobbyFullError(LobbyFullException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new DefaultErrorResponse("LOBBY_FULL", e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<DefaultErrorResponse> handleLobbyNotFoundError(LobbyNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new DefaultErrorResponse("LOBBY_NOT_FOUND", e.getMessage()));
    }
}
