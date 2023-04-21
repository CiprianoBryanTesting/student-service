package com.scotia.student.storage.service.impl;

import com.scotia.student.storage.config.exception.*;
import com.scotia.student.storage.controller.dto.*;
import com.scotia.student.storage.repository.*;
import com.scotia.student.storage.repository.entity.*;
import com.scotia.student.storage.service.*;
import com.scotia.student.storage.util.enums.*;
import com.scotia.student.storage.util.mapper.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        return studentMapper.toDTO(studentRepository.save(student));
    }

    @Transactional
    @Override
    public StudentDTO update(Integer id, StudentDTO studentDTO) {
        if (!studentRepository.existsById(id)) {
            log.info(StudentResponse.STUDENT_NOT_FOUND.getMessage());
            throw new BusinessException(StudentResponse.STUDENT_NOT_FOUND);
        }
        Student student = studentMapper.toEntity(studentDTO);
        return studentMapper.toDTO(studentRepository.save(student));
    }

    @Override
    public void delete(Integer id) {
        if (!studentRepository.existsById(id)) {
            log.info(StudentResponse.STUDENT_NOT_FOUND.getMessage());
            throw new BusinessException(StudentResponse.STUDENT_NOT_FOUND);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> getAll() {
        return studentMapper.toDTO(studentRepository.findAll());
    }

    @Override
    public List<StudentDTO> getActives() {
        return studentMapper.toDTO(studentRepository.findAllByStatusIsTrue());
    }
}
