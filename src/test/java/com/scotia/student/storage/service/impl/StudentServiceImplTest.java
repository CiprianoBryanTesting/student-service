package com.scotia.student.storage.service.impl;

import com.scotia.student.storage.controller.dto.*;
import com.scotia.student.storage.repository.*;
import com.scotia.student.storage.repository.entity.*;
import com.scotia.student.storage.service.*;
import com.scotia.student.storage.util.mapper.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.beans.factory.annotation.*;
import reactor.core.publisher.*;
import reactor.test.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Autowired
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapperImpl();
        studentService = new StudentServiceImpl(studentRepository, studentMapper);
    }

    @Test
    void saveOk() {
        StudentDTO studentToSave = getStudentDTO(1, 13);
        Student savedStudent = getStudent(1, 13);

        Mockito.when(studentRepository.existsById(1)).thenReturn(Mono.just(Boolean.FALSE));
        Mockito.when(studentRepository.save(Mockito.any())).thenReturn(Mono.just(savedStudent));

        Mono<StudentDTO> studentExpected = studentService.save(studentToSave);
        StepVerifier.create(studentExpected).expectNext(studentToSave).verifyComplete();
    }

    @Test
    void getAllOk() {
        Student savedStudent1 = getStudent(2, 13);
        Student savedStudent2 = getStudent(3, 14);
        Student savedStudent3 = getStudent(4, 15);

        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentDTOList.add(getStudentDTO(2, 13));
        studentDTOList.add(getStudentDTO(3, 14));
        studentDTOList.add(getStudentDTO(4, 15));

        Mockito.when(studentRepository.findAll()).thenReturn(Flux.just(savedStudent1, savedStudent2, savedStudent3));

        Mono<List<StudentDTO>> studentsExpected = studentService.getAll();
        StepVerifier.create(studentsExpected).expectNext(studentDTOList).verifyComplete();
    }

    @Test
    void getActivesOk() {
        Student savedStudent1 = getStudent(2, 13);
        Student savedStudent2 = getStudent(4, 15);

        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentDTOList.add(getStudentDTO(2, 13));
        studentDTOList.add(getStudentDTO(4, 15));

        Mockito.when(studentRepository.findAllByStatusIsTrue()).thenReturn(Flux.just(savedStudent1, savedStudent2));

        Mono<List<StudentDTO>> studentsExpected = studentService.getActives();
        StepVerifier.create(studentsExpected).expectNext(studentDTOList).verifyComplete();
    }

    private StudentDTO getStudentDTO(Integer id, Integer age) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(id);
        studentDTO.setName("Name");
        studentDTO.setLastname("Lastname");
        studentDTO.setStatus("activo");
        studentDTO.setAge(age);
        return studentDTO;
    }

    private Student getStudent(Integer id, Integer age) {
        Student student = new Student();
        student.setId(id);
        student.setName("Name");
        student.setLastname("Lastname");
        student.setStatus(true);
        student.setAge(age);
        return student;
    }
}