package com.scotia.student.storage.util.enums;

import lombok.*;
import org.springframework.http.*;

@Getter
@AllArgsConstructor
public enum StudentResponse {
    STUDENT_STATUS_INCORRECT(HttpStatus.BAD_REQUEST, "El valor del campo status es incorrecto"),
    STUDENT_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "El ID ingresado ya se encuentra registrado"),
    ERROR_SAVE_STUDENT(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar al alumno en la DB. Contactar con el administrador."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Existe un error en el servicio de alumnos. Contactar con el administrador.");

    private final HttpStatus status;
    private final String message;
}
