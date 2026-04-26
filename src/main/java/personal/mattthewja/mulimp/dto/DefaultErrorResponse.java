package personal.mattthewja.mulimp.dto;

public class DefaultErrorResponse {
    private String code;
    private String message;

    public DefaultErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
