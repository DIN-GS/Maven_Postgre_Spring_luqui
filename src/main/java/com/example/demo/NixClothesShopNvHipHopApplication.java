package com.example.demo;

import com.example.demo.service.PhotoService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class NixClothesShopNvHipHopApplication {

	@Autowired
	private StudentService service;

	@Autowired
	private PhotoService photoService;


	public static void main(String[] args) {
		SpringApplication.run(NixClothesShopNvHipHopApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext ctx){

		return (args -> {
			/*service.createStudent("Mark", "mark@gmail.com");
            service.createStudent("Max", "max@gmail.com");*/
//			service.findAll().forEach(System.out::println);
			//service.deleteStudentEntityById(1L);

//			photoService.createPhotoEntity("llll", "first", 1L);
//			photoService.createPhotoEntity("llhyj", "second", 1L);
//			photoService.createPhotoEntity("erer", "third", 2L);
//
//			photoService.getPhotoByDescription("first").forEach(photo -> System.out.println(photo.getUrl()));

		});
	}



}
