package com.rislah.tpilet.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomErrorResponse {
    private HttpStatus status;
    private String message;
    private String debugMessage;
    private List<ValidationError> validationErrors;

    public CustomErrorResponse(HttpStatus status, Throwable throwable) {
        this.status = status;
        this.message = "Unknown error";
        this.debugMessage = throwable.getLocalizedMessage();
    }

    public CustomErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CustomErrorResponse(HttpStatus status, String message, Throwable throwable) {
        this.status = status;
        this.message = message;
        this.debugMessage = throwable.getLocalizedMessage();
    }

    public CustomErrorResponse(HttpStatus status, String message, List<ValidationError> validationErrors) {
        this.status = status;
        this.message = message;
        this.validationErrors = validationErrors;
    }
}
