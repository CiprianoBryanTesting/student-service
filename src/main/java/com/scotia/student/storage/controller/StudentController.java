package com.scotia.student.storage.controller;

import com.scotia.student.storage.controller.dto.*;
import com.scotia.student.storage.service.*;
import com.scotia.student.storage.util.enums.*;
import io.github.resilience4j.circuitbreaker.annotation.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

import javax.validation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> create(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.save(studentDTO).map(ResponseEntity::ok);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name = "get-all", fallbackMethod = "apiFallBackGetAll")
    public Mono<ResponseEntity<?>> getAll() {
        return studentService.getAll().map(ResponseEntity::ok);
    }

    @GetMapping(value = "/actives", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getActives() {
        return studentService.getActives().map(ResponseEntity::ok);
    }

    public Mono<ResponseEntity<?>> apiFallBackGetAll(Exception exception) {
        log.info(StudentResponse.INTERNAL_SERVER_ERROR.getMessage() + ", Error={}", exception.getMessage());
        return Mono.just(ResponseEntity.noContent().build());
    }
}
