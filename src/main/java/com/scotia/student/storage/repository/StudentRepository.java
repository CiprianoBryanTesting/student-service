package com.scotia.student.storage.repository;

import com.scotia.student.storage.repository.entity.*;
import org.springframework.data.r2dbc.repository.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

@Repository
public interface StudentRepository extends R2dbcRepository<Student, Integer> {
    Flux<Student> findAllByStatusIsTrue();
}
