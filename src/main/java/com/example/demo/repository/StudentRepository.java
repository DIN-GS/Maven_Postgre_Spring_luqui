package com.example.demo.repository;


import com.example.demo.repository.entity.StudentEntity;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import static org.hibernate.sql.ast.Clause.WHERE;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findStudentEntityById(Long id);


    void deleteStudentEntityById(@Param("student_id") Long student_id);
}
