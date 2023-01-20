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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public int id;

    @Column
    public String name;

    @Column
    public String email;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade =  CascadeType.REMOVE)
    private List<PhotoEntity> photo;
}
