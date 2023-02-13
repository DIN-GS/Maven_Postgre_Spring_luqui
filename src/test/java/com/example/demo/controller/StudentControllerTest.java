package com.example.demo.controller;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.entity.StudentEntity;
import com.example.demo.service.StudentService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @InjectMocks
    private StudentController studentController;
    @Mock
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;

    private MockMvc mockMvc;
    private StudentDTO studentDTO;
    private StudentEntity studentEntity;
   /* @BeforeEach
    public void initialize(){
        studentDTO = StudentMapper.INSTANCE.toDTO(studentService.findStudentEntityById(2L));
    }*/

    @Test
    void shouldGetStudentById() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");

        HttpUriRequest request = new HttpGet("http://localhost:8080/students/2");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        int a = httpResponse.getStatusLine().getStatusCode();
        assertThat(a, equalTo(HttpStatus.SC_OK));
    }



    @Test
    void shouldGiveNotFoundWhenDeleteStudentEntityById() throws IOException{
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");

        HttpUriRequest request = new HttpGet("http://localhost:8080/studentDelete/4");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        int a = httpResponse.getStatusLine().getStatusCode();
        assertThat(a, equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    void shouldGiveStudents() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C\\path\\to\\chromedriver.exe");
        try {
            HttpUriRequest request = new HttpGet("http//localhost:8080/students");


            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
            assertNotEquals(HttpStatus.SC_NO_CONTENT, httpResponse.getStatusLine().getStatusCode());
        } catch (ClientProtocolException exception){
            System.out.println("oops");
        }

    }

    //doesn't work(
    @Test
    void shouldIdBeTheSame() throws StudentNotFoundException {

            //when(studentService.findStudentEntityById(Long.valueOf(anyString()))).thenReturn(studentDTO);

            Mockito.when(studentService.findStudentEntityById(anyLong())).thenReturn(getStudent());
            studentDTO = StudentMapper.INSTANCE.toDTO(studentService.findStudentEntityById(2L));
            try {
                StudentDTO studentDTO1 = studentController.getStudentById(2L).getBody();
                assertNotNull(studentDTO1);
                assertEquals(studentDTO, studentDTO1);
            }
            catch (NoSuchElementException exception){
                System.out.println("oops");
            }


    }

    @Test
    void shouldCreateStudent() throws IOException{
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");
       /* studentEntity = new StudentEntity();
        studentEntity.setName("Vlad");
        studentEntity.setEmail("vlad@gmail.com");*/

        /*URL url = new URL("http://localhost:8080/students/add");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);*/
        String jsonInputString = "{\"name\": \"Vlad\", \"email\": \"vlad@gmail.com\"}";

        StringEntity requestEntity = new StringEntity(
                jsonInputString,
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost("http://localhost:8080/students/add");
        postMethod.setEntity(requestEntity);

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(postMethod);

        int a = httpResponse.getStatusLine().getStatusCode();
        assertThat(a, equalTo(HttpStatus.SC_CREATED));

        /*try {
            Mockito.when(studentController.createStudent(studentDTO)).thenReturn(new ResponseEntity<>(studentDTO, org.springframework.http.HttpStatus.CREATED));
            Mockito.when(studentController.getStudentById(anyLong())).thenReturn(new ResponseEntity<>(studentDTO, org.springframework.http.HttpStatus.OK));
            //Mockito.when(studentService.createStudent()).thenReturn(studentEntity);
            //Mockito.when(studentRepository.findStudentEntityById(4L)).thenReturn(studentEntity);
            ResponseEntity<StudentDTO> studentDTOResponseEntity = studentController.createStudent(studentDTO);
            assertNotNull(studentDTOResponseEntity);
            try{
                StudentEntity student = StudentMapper.INSTANCE.toEntity(studentController.getStudentById(4L).getBody());
                assertNotNull(student);
                //assumeTrue(studentEntity.getName().equals(student.getName()));
                assertLinesMatch(studentEntity.getEmail().lines(), student.getEmail().lines());
            } catch (NoSuchElementException exception){
                System.out.println("Student wasn't created");
            }
        } catch (UnnecessaryStubbingException exception){

        }*/


    }



    private StudentEntity getStudent(){
        studentEntity = new StudentEntity();
        studentEntity.setEmail("max@gmail.com");
        studentEntity.setName("Max");
        studentEntity.setId(2L);
        return studentEntity;
    }
}