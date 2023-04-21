package com.scotia.student.storage.controller;

import com.scotia.student.storage.config.exception.*;
import com.scotia.student.storage.controller.dto.*;
import com.scotia.student.storage.service.*;
import com.scotia.student.storage.util.constants.*;
import com.scotia.student.storage.util.enums.*;
import io.github.resilience4j.circuitbreaker.annotation.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO studentDTO) {
        ValidateFields(studentDTO);
        return ResponseEntity.ok(studentService.save(studentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Integer id, @Valid @RequestBody StudentDTO studentDTO) {
        ValidateFields(studentDTO);
        return ResponseEntity.ok(studentService.update(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name = "get-all", fallbackMethod = "apiFallBackGetAll")
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping(value = "/actives", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getActives() {
        return ResponseEntity.ok(studentService.getActives());
    }

    private void ValidateFields(StudentDTO studentDTO) {
        if (!studentDTO.getStatus().equals(StatusConstants.ACTIVE.getDescription()) &&
                !studentDTO.getStatus().equals(StatusConstants.INACTIVE.getDescription())) {
            throw new BusinessException(StudentResponse.STUDENT_STATUS_INCORRECT);
        }
    }

    public ResponseEntity<?> apiFallBackGetAll(Exception exception) {
        log.info(StudentResponse.INTERNAL_SERVER_ERROR.getMessage() + ", Error={}", exception.getMessage());
        return ResponseEntity.noContent().build();
    }
}
