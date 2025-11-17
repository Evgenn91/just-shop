package evn.petproject.just_shop.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String path; // можно добавить путь запроса
    private Instant timestamp = Instant.now();

    public ErrorResponse(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
