package application.model.response;

import org.springframework.http.HttpStatus;

public class StatusResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
