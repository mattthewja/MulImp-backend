package personal.mattthewja.mulimp.dto;

import lombok.Getter;

@Getter
public class PostPlayerVoteRequest {
    private final String vote;

    public PostPlayerVoteRequest(String vote) {
        this.vote = vote;
    }
}
