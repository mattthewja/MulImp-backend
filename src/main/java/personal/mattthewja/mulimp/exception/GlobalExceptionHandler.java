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
}
