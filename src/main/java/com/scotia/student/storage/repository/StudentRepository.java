package com.scotia.student.storage.repository;

import com.scotia.student.storage.repository.entity.*;
import org.springframework.data.repository.reactive.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Integer> {
    Flux<Student> findAllByStatusIsTrue();
}
