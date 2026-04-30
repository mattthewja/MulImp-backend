package personal.mattthewja.mulimp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostPlayerAnswerRequest {
    @NotBlank(message = "answer cannot be blank")
    @Size(max = 300, message = "answer is too long")
    private final String answer;
    
    public PostPlayerAnswerRequest(String answer) {
        this.answer = answer;
    }
}
