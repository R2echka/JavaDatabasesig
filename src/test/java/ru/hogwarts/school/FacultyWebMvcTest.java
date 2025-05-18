package ru.hogwarts.school;

import java.util.Optional;

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
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

@WebMvcTest
public class FacultyWebMvcTest {
    
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
    private FacultyController facultyController;

    @Test
    public void createFacultyTest() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "test");
        facultyObject.put("color", "color");

        Faculty faculty = new Faculty();
        faculty.setId(1);
        faculty.setName("test");
        faculty.setColor("color");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
            .post("/faculty")
            .content(facultyObject.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("test"))
            .andExpect(jsonPath("$.color").value("color"));
    }

    @Test
    public void findFacultyTest() throws Exception {

        Faculty faculty = new Faculty();
        faculty.setId(1);
        faculty.setName("test");
        faculty.setColor("color");

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
            .get("/faculty/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("test"))
            .andExpect(jsonPath("$.color").value("color"));
    }

    @Test
    public void updateFacultyTest() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "test");
        facultyObject.put("color", "color");

        Faculty faculty = new Faculty();
        faculty.setId(1);
        faculty.setName("test");
        faculty.setColor("color");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
            .put("/faculty/1")
            .content(facultyObject.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("test"))
            .andExpect(jsonPath("$.color").value("color"));
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .delete("/faculty/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void findStudentsFacultyTest() throws Exception {

        Faculty faculty = new Faculty();
        faculty.setId(1);
        faculty.setName("test");
        faculty.setColor("color");

        when(facultyRepository.findByStudentsId(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
            .get("/faculty/students/3482")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("test"))
            .andExpect(jsonPath("$.color").value("color"));
    }
}