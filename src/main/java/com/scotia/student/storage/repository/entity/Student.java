package com.scotia.student.storage.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private boolean status;
    private Integer age;
}
