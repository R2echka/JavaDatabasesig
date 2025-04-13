package ru.hogwarts.school;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import net.minidev.json.JSONObject;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

@WebMvcTest
public class StudentWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private FacultyRepository facultyRepository;

    @SuppressWarnings("removal")
    @MockBean
    private StudentRepository studentRepository;

    @SuppressWarnings("removal")
    @SpyBean
    private FacultyService facultyService;

    @SuppressWarnings("removal")
    @SpyBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void createStudentTest() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "test");
        studentObject.put("age", "1");

        Student student = new Student();
        student.setId(1);
        student.setName("test");
        student.setAge(1);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
            .post("/student")
            .content(studentObject.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("test"))
            .andExpect(jsonPath("$.age").value("1"));
    }

    @Test
    public void findStudentTest() throws Exception {

        Student student = new Student();
        student.setId(1);
        student.setName("test");
        student.setAge(1);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
            .get("/student/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("test"))
            .andExpect(jsonPath("$.age").value("1"));
    }

    @Test
    public void findAllStudentTest() throws Exception {

        when(studentRepository.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders
            .get("/student")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
}

    @Test
    public void updateStudentTest() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "test");
        studentObject.put("age", "1");

        Student student = new Student();
        student.setId(1);
        student.setName("test");
        student.setAge(1);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
            .put("/student/1")
            .content(studentObject.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("test"))
            .andExpect(jsonPath("$.age").value("1"));
    }

    @Test
    public void deleteStudentTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .delete("/student/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void findStudentsFacultyTest() throws Exception {

        Student student = new Student();
        student.setId(1);
        student.setName("test");
        student.setAge(1);

        Collection<Student> students = new ArrayList<>();
        students.add(student);

        when(studentRepository.findByFacultyId(any(Long.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
            .get("/faculty/students/3482")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
