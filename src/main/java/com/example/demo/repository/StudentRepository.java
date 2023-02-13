package com.example.demo.repository;


import com.example.demo.repository.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findStudentEntityById(Long id);

    @Modifying
    @Query("update StudentEntity s  set s.name = ?2, s.email = ?3 where s.id = ?1")
    StudentEntity updateStudentEntityById(Long id,String name, String email);


    void deleteStudentEntityById(@Param("student_id") Long student_id);
}
