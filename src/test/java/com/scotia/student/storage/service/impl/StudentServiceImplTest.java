package com.scotia.student.storage.service.impl;

import com.scotia.student.storage.repository.*;
import com.scotia.student.storage.service.*;
import com.scotia.student.storage.util.mapper.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveOk() {

    }
}