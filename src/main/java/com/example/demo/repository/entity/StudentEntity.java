package com.example.demo.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "student", schema = "nix")
public class StudentEntity {

    @Id
    @GeneratedValue
    @Column
    public int id;

    @Column
    public String name;

    @Column
    public int age;

    @Column
    public String email;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade =  CascadeType.REMOVE)
    private List<PhotoEntity> photo;
}
