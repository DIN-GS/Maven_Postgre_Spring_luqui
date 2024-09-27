package com.example.demo.service;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.exception.InvalidArgumentsException;
import com.example.demo.exception.StudentDoesNotExistException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.entity.StudentEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    private static StudentDTO studentDTO;

    private static StudentEntity studentEntity;

    private StudentEntity student = mock(StudentEntity.class);
    private StudentRepository studentRepositoryMock = mock(StudentRepository.class);

    private final FakeStudentService fakeStudentRepository = new FakeStudentService(studentRepository);

    @BeforeAll
    public static void initialize(){
        studentDTO = new StudentDTO();
        studentDTO.setName("MaxMir");
        studentDTO.setEmail("maxMir@gmail.com");
        studentEntity = new StudentEntity();
        studentEntity.setName("Max");
        studentEntity.setEmail("max@gmail.com");
    }

    @Test
    void createStudent() throws InvalidArgumentsException, StudentNotFoundException {
        Mockito.when(studentService.createStudent(studentEntity)).thenReturn(fakeStudentRepository.createStudent(studentEntity));
        studentService.createStudent(studentEntity);
        StudentEntity student = fakeStudentRepository.findStudentEntityById(1L);
        assertNotNull(student);
        assertEquals(studentEntity.getEmail(), student.getEmail());
    }

    //?
    @Test
    void updateStudent() throws StudentNotFoundException, StudentDoesNotExistException {
        StudentService service = new StudentService(studentRepositoryMock);
        Mockito.when(studentRepositoryMock.findStudentEntityById(anyLong())).thenReturn(studentEntity);
        service.updateStudentById(studentDTO,2L);
        verify(studentRepositoryMock).save(any());
    }

    @Test
    void findStudentEntityById() throws StudentNotFoundException{
        StudentService service = new StudentService(studentRepositoryMock);
        Mockito.when(studentRepositoryMock.findStudentEntityById(anyLong())).thenReturn(studentEntity);
        studentEntity =  service.findStudentEntityById(2L);
        assertNotNull(studentEntity);
        assertEquals("Max", studentEntity.getName());
        assertEquals("max@gmail.com",studentEntity.getEmail());
    }

    @Test
    void findAll() {
        StudentService service = new StudentService(studentRepositoryMock);
        Mockito.when(studentRepositoryMock.findAll()).thenReturn(fakeStudentRepository.findAll());
        assertEquals(3, service.findAll().size());
    }

    @Test
    void deleteStudentEntityById() {
        try{
            StudentService service = new StudentService(studentRepositoryMock);
            Mockito.when(studentRepositoryMock.deleteStudentEntityById(2L)).thenReturn(fakeStudentRepository.deleteStudentEntityById(2L));
            service.deleteStudentEntityById(2L);
            fakeStudentRepository.findStudentEntityById(2L);
        } catch (StudentNotFoundException exception){
            assertEquals("Student wasn't found", exception.toString());
        }
    }
}