package com.scotia.student.storage.repository.entity;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student implements Persistable<Integer> {
    @Id
    private Integer id;
    private String name;
    private String lastname;
    private boolean status;
    private Integer age;

    @Transient
    private boolean isNewStudent;

    @Override
    @Transient
    public boolean isNew() {
        return this.isNewStudent || this.id == null;
    }

    public Student setAsNew() {
        this.isNewStudent = true;
        return this;
    }
}
