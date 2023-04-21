package com.scotia.student.storage.config.exception;

import com.scotia.student.storage.util.enums.*;
import lombok.*;
import org.springframework.http.*;

import java.time.*;

@Data
@Builder
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
    private final LocalDateTime date;

    public BusinessException(StudentResponse response) {
        this.httpStatus = response.getStatus();
        this.message = response.getMessage();
        this.date = LocalDateTime.now();
    }
}
