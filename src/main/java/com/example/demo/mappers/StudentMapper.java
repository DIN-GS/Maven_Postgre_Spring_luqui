package com.example.demo.mappers;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.repository.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    //@Mapping(source = "photo", target = "photoDTOList")
    //@Mapping(source = "id", target = "id", qualifiedByName = "longConverter")
    StudentDTO toDTO(StudentEntity studentEntity);

    StudentEntity toEntity(StudentDTO studentDTO);

    //@Mapping(source = "photo", target = "photoDTOList")
    List<StudentDTO> studentsToDTO(List<StudentEntity> studentEntities);

    @Named("longConverter")
    static String longIdToString(Long studentId){
        return studentId+"";
    }


}
