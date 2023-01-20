package com.example.demo;

import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NixClothesShopNvHipHopApplication {

	@Autowired
	private StudentService service;


	public static void main(String[] args) {
		SpringApplication.run(NixClothesShopNvHipHopApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner run(TeacherRepository repository){
		return (args -> {
			insertJavTeachers(repository);
			System.out.println(repository.findAll());
		});
	}

	private void insertJavTeachers(TeacherRepository repository){
		repository.save(new Teacher("Severuna Olena Viktorivna",55, "KSS№62", "A+"));
	}*/

}
