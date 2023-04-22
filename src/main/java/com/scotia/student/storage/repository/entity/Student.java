package com.scotia.student.storage.repository.entity;

import lombok.*;
import org.springframework.data.annotation.*;

@Data
public class Student {
    @Id
    private Integer id;
    private String name;
    private String lastname;
    private boolean status;
    private Integer age;
}
