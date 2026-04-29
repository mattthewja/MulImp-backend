package personal.mattthewja.mulimp.dto;

import lombok.Getter;

@Getter
public class StartGameResponse {
    private final boolean successful;

    public StartGameResponse(boolean successful) {
        this.successful = successful;
    }
}
