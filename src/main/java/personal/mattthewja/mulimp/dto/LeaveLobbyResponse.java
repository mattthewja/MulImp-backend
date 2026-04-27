package personal.mattthewja.mulimp.dto;

public class LeaveLobbyResponse {
    private final Boolean successful;

    public LeaveLobbyResponse(Boolean successful) {
        this.successful = successful;
    }

    public Boolean getSuccessful() {
        return successful;
    }
}
