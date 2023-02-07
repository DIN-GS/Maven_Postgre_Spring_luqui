package com.example.demo.mappers;

import com.example.demo.controller.dto.PhotoDTO;
import com.example.demo.repository.entity.PhotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PhotoMapper {
    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);
    @Mapping(source = "student", target = "studentDTO")
    PhotoDTO  toDTO(PhotoEntity photoEntity);

    @Mapping(source = "student", target = "studentDTO")
    List<PhotoDTO> photosToDTO(List<PhotoEntity> photoEntities);

}
