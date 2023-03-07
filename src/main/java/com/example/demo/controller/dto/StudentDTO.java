package com.example.demo.controller.dto;

import com.example.demo.repository.entity.PhotoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;
    private String email;

    private String password;
    ///private List<PhotoDTO> photoDTOList;

    /*@Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", photoDTOList=" + photoDTOList +
                '}';
    }*/
}
