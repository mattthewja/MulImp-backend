package personal.mattthewja.mulimp.dto;

import lombok.Getter;

@Getter
public class PostPlayerVoteResponse {
    private final boolean successful;

    public PostPlayerVoteResponse(boolean successful) {
        this.successful = successful;
    }
}
