package com.scotia.student.storage.util.mapper;

import com.scotia.student.storage.controller.dto.*;
import com.scotia.student.storage.repository.entity.*;
import com.scotia.student.storage.util.constants.*;
import org.mapstruct.*;

import java.util.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "status", expression = "java(statusToEntity(studentDTO.getStatus()))")
    Student toEntity(StudentDTO studentDTO);

    @Mapping(target = "status", expression = "java(statusToDTO(student.isStatus()))")
    StudentDTO toDTO(Student student);

    List<StudentDTO> toDTOList(List<Student> students);

    default boolean statusToEntity(String status) {
        return StatusConstants.findByDescription(status).getValue();
    }

    default String statusToDTO(boolean status) {
        return StatusConstants.findByValue(status).getDescription();
    }
}
