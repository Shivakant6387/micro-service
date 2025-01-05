package org.example.loansservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Schema(
        name = "Response",
        description = "Schema to hold successfully information"
)
public class ResponseDto {
    private String statusCode;
    private String message;

    public ResponseDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResponseDto() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

