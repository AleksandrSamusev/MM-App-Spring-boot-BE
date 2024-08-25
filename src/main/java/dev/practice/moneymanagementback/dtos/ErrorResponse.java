package dev.practice.moneymanagementback.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String path;
    private HttpStatus errorCode;
    private List<String> errorMessages = new ArrayList<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(List<String> errorMessages) {
        this.timestamp = LocalDateTime.now();
        this.errorMessages = errorMessages;
    }

    public void addError(String message) {
        this.errorMessages.add(message);
    }
}
