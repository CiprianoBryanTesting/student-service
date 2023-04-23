package com.scotia.student.storage.service;

import com.scotia.student.storage.controller.dto.*;
import reactor.core.publisher.*;

import java.util.*;

public interface StudentService {
    Mono<StudentDTO> save(StudentDTO studentDTO);
    Mono<List<StudentDTO>> getAll();
    Mono<List<StudentDTO>> getActives();
}
