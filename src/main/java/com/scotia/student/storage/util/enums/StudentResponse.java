package com.scotia.student.storage.util.enums;

import lombok.*;
import org.springframework.http.*;

@Getter
@AllArgsConstructor
public enum StudentResponse {
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "No se encontró al alumno"),
    STUDENT_STATUS_INCORRECT(HttpStatus.INTERNAL_SERVER_ERROR, "El valor del campo status es incorrecto");

    private final HttpStatus status;
    private final String message;
}