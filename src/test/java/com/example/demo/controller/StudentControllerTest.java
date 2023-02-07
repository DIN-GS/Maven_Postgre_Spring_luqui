package com.example.demo.controller;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.repository.entity.StudentEntity;
import com.example.demo.service.StudentService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @InjectMocks
    private StudentController studentController;
    @Mock
    private StudentService studentService;

    private StudentDTO studentDTO;
    private StudentEntity studentEntity;
   /* @BeforeEach
    public void initialize(){
        studentDTO = StudentMapper.INSTANCE.toDTO(studentService.findStudentEntityById(2L));
    }*/

    /*@Test
    void shouldGetStudentById() throws IOException, ClientProtocolException {
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");

        HttpUriRequest request = new HttpGet("http://localhost:8080/students/2");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        int a = httpResponse.getStatusLine().getStatusCode();
        assertThat(a, equalTo(HttpStatus.SC_OK));
    }*/



    @Test
    void deleteStudentEntityById() {
    }

    @Test
    void getAllStudents() throws IOException {

    }

    //doesn't work(
    /*@Test
    void shouldIdBeTheSame() {

            //when(studentService.findStudentEntityById(Long.valueOf(anyString()))).thenReturn(studentDTO);

            Mockito.when(studentService.findStudentEntityById(anyLong())).thenReturn(getStudent());
            studentDTO = StudentMapper.INSTANCE.toDTO(studentService.findStudentEntityById(2L));
            StudentDTO studentDTO1 = studentController.getStudentById(2L).getBody();
            assertNotNull(studentDTO1);
            assertEquals(studentDTO, studentDTO1);

    }*/

    private StudentEntity getStudent(){
        studentEntity = new StudentEntity();
        studentEntity.setEmail("max@gmail.com");
        studentEntity.setName("Max");
        studentEntity.setId(2L);
        return studentEntity;
    }
}