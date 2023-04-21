package com.scotia.student.storage.controller.dto;

import lombok.*;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;

@Data
public class StudentDTO {
    @NotNull(message = "Debe ingresar su ID")
    @Min(1)
    private Integer id;
    @NotNull(message = "Debe ingresar su nombre")
    private String name;
    @NotNull(message = "Debe ingresar su apellido")
    private String lastname;
    private String status;
    @NotNull(message = "Debe ingresar su edad")
    @Range(min = 12, max = 18)
    private Integer age;
}
