package personal.mattthewja.mulimp.dto;

import lombok.Getter;

@Getter
public class PostPlayerAnswerResponse {
    private final boolean successful;

    public PostPlayerAnswerResponse(boolean successful) {
        this.successful = successful;
    }
}
