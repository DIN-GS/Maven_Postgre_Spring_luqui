package com.example.demo.repository;

import com.example.demo.repository.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {

    List<PhotoEntity> findByDescription(String description);
}
