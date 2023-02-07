package com.example.demo.service;

import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.entity.PhotoEntity;
import com.example.demo.repository.entity.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository repository;

    private final StudentRepository studentRepository;

    public void createPhotoEntity(String url, String decription, long student_id) throws StudentNotFoundException {

        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setUrl(url);
        photoEntity.setDescription(decription);
        photoEntity.setStudent(studentRepository.findById(student_id).orElseThrow(() -> new StudentNotFoundException("Student wasn't found")));
        repository.save(photoEntity);
    }

    public List<PhotoEntity> getPhotoByDescription(String description){
        return repository.findByDescription(description);
    }
}
