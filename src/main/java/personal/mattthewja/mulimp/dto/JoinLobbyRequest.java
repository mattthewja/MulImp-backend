package personal.mattthewja.mulimp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class JoinLobbyRequest {
    @NotBlank(message = "Username is required")
    @Size(max = 20, message = "Username too long")
    private String username;
}
