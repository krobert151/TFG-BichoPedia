package com.robertorebolledonaharro.bichoapi.common.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public  class ErrorMessage {

    private HttpStatus status;
    private String message, path;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateTime = LocalDateTime.now();

    public static ErrorMessage of (HttpStatus status, String message, String path) {
        return ErrorMessage.builder()
                .status(status)
                .message(message)
                .path(path)
                .build();
    }

}