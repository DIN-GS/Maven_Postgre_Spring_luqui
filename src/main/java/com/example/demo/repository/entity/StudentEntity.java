package com.example.demo.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "student", schema = "nix")

public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    @Column
    public String name;

    @Column
    public String email;

    public StudentEntity(){}

    /*public StudentEntity(String name, String email){

        this.name = name;
        this.email = email;
    }*/

    public void setPhoto(List<PhotoEntity> photo){
        this.photo = new ArrayList<>();
        Collections.copy(this.photo, photo);
    }

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade =  CascadeType.REMOVE)
    private List<PhotoEntity> photo;
}
