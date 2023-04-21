package com.scotia.student.storage.repository;

import com.scotia.student.storage.repository.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByStatusIsTrue();
}
