package com.scotia.student.storage.service;

import com.scotia.student.storage.controller.dto.*;

import java.util.*;

public interface StudentService {
    StudentDTO save(StudentDTO studentDTO);
    StudentDTO update(Integer id, StudentDTO studentDTO);
    void delete(Integer id);
    List<StudentDTO> getAll();
    List<StudentDTO> getActives();
}
