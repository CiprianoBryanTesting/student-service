package com.scotia.student.storage.service.impl;

import com.scotia.student.storage.config.exception.*;
import com.scotia.student.storage.controller.dto.*;
import com.scotia.student.storage.repository.*;
import com.scotia.student.storage.repository.entity.*;
import com.scotia.student.storage.service.*;
import com.scotia.student.storage.util.constants.*;
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

    @Transactional
    @Override
    public Mono<StudentDTO> save(StudentDTO studentDTO) {
        return studentRepository.existsById(studentDTO.getId())
                .flatMap(exists -> {
                    if (exists) {
                        log.info(StudentResponse.STUDENT_ALREADY_REGISTERED.getMessage());
                        return Mono.error(new BusinessException(StudentResponse.STUDENT_ALREADY_REGISTERED));
                    }
                    BusinessException exception = validateFields(studentDTO);
                    if (exception != null) {
                        return Mono.error(exception);
                    }
                    Student student = studentMapper.toEntity(studentDTO);
                    return studentRepository.save(student.setAsNew())
                            .map(savedStudent -> studentMapper.toDTO(savedStudent))
                            .switchIfEmpty(Mono.error(new BusinessException(StudentResponse.ERROR_SAVE_STUDENT)));
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

    private BusinessException validateFields(StudentDTO studentDTO) {
        if (!studentDTO.getStatus().equals(StatusConstants.ACTIVE.getDescription()) &&
                !studentDTO.getStatus().equals(StatusConstants.INACTIVE.getDescription())) {
            return new BusinessException(StudentResponse.STUDENT_STATUS_INCORRECT);
        }
        return null;
    }
}
