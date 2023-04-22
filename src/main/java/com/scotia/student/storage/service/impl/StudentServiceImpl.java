package com.scotia.student.storage.service.impl;

import com.scotia.student.storage.config.exception.*;
import com.scotia.student.storage.controller.dto.*;
import com.scotia.student.storage.repository.*;
import com.scotia.student.storage.repository.entity.*;
import com.scotia.student.storage.service.*;
import com.scotia.student.storage.util.enums.*;
import com.scotia.student.storage.util.mapper.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import reactor.core.publisher.*;

import java.util.*;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Mono<StudentDTO> save(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        return studentRepository.save(student)
                .map(savedStudent -> studentMapper.toDTO(savedStudent))
                .switchIfEmpty(Mono.error(new BusinessException(StudentResponse.INTERNAL_SERVER_ERROR)));
    }

    @Transactional
    @Override
    public Mono<StudentDTO> update(Integer id, StudentDTO studentDTO) {
        return studentRepository.existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        log.info(StudentResponse.STUDENT_NOT_FOUND.getMessage());
                        return Mono.error(new BusinessException(StudentResponse.STUDENT_NOT_FOUND));
                    }
                    Student student = studentMapper.toEntity(studentDTO);
                    return studentRepository.save(student)
                            .map(studentMapper::toDTO);
                });
    }

    @Transactional
    @Override
    public Mono<Void> delete(Integer id) {
        return studentRepository.existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        log.info(StudentResponse.STUDENT_NOT_FOUND.getMessage());
                        return Mono.error(new BusinessException(StudentResponse.STUDENT_NOT_FOUND));
                    }
                    return studentRepository.deleteById(id);
                });
    }

    @Override
    public Mono<List<StudentDTO>> getAll() {
        return studentRepository.findAll().collectList()
                .map(students -> studentMapper.toDTOList(students));
    }

    @Override
    public Mono<List<StudentDTO>> getActives() {
        return studentRepository.findAllByStatusIsTrue().collectList()
                .map(students -> studentMapper.toDTOList(students));
    }
}
