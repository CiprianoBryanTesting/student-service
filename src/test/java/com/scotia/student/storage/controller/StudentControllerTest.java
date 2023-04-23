package com.scotia.student.storage.controller;

import com.scotia.student.storage.config.*;
import com.scotia.student.storage.controller.dto.*;
import com.scotia.student.storage.service.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.reactive.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.test.web.reactive.server.*;
import reactor.core.publisher.*;

import java.util.*;

@WebFluxTest(controllers = StudentController.class)
@AutoConfigureWebTestClient
@Import(R2dbcConfig.class)
class StudentControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private StudentService studentService;

    private final String SAVE_URL = "/student";
    private final String GET_ALL_URL = "/student/all";
    private final String GET_ACTIVES_URL = "/student/actives";

    @Test
    void successfulCreate() {
        StudentDTO studentDTO = getStudentDTO(1, 13);

        Mockito.when(studentService.save(studentDTO)).thenReturn(Mono.just(studentDTO));

        webTestClient
                .post()
                .uri(SAVE_URL)
                .bodyValue(studentDTO)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(StudentDTO.class)
                .consumeWith(response -> {
                    StudentDTO student = response.getResponseBody();
                    Assertions.assertEquals(1, student.getId());
                });
    }

    @Test
    void failedCreate() {
        StudentDTO studentDTO = getStudentDTO(1, 30);

        Mockito.when(studentService.save(studentDTO)).thenReturn(Mono.just(studentDTO));

        webTestClient
                .post()
                .uri(SAVE_URL)
                .bodyValue(studentDTO)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    void successfulgetAllOk() {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentDTOList.add(getStudentDTO(1, 13));
        studentDTOList.add(getStudentDTO(2, 15));

        Mockito.when(studentService.getAll()).thenReturn(Mono.just(studentDTOList));

        webTestClient
                .get()
                .uri(GET_ALL_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(List.class)
                .consumeWith(response -> {
                    List<StudentDTO> student = response.getResponseBody();
                    Assertions.assertEquals(2, student.size());
                });
    }

    @Test
    void successfulGetActives() {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentDTOList.add(getStudentDTO(3, 12));
        studentDTOList.add(getStudentDTO(5, 16));
        studentDTOList.add(getStudentDTO(6, 16));

        Mockito.when(studentService.getActives()).thenReturn(Mono.just(studentDTOList));

        webTestClient
                .get()
                .uri(GET_ACTIVES_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(List.class)
                .consumeWith(response -> {
                    List<StudentDTO> student = response.getResponseBody();
                    Assertions.assertEquals(3, student.size());
                });
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
}