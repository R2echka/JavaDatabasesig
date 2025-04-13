package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class FacultyRestTemplateTest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("test");
        faculty.setColor("color");
        Faculty response = restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        Assertions.
        assertThat(response).
        isNotNull();

        restTemplate.delete("http://localhost:" + port + "/faculty/" + response.getId());
    }

    @Test
    public void testGetFaculty() throws Exception {
        Faculty f = new Faculty();
        f.setName("test");
        f.setColor("color");
        Faculty faculty = restTemplate.postForObject("http://localhost:" + port + "/faculty", f, Faculty.class);
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + faculty.getId(), String.class);
        Assertions.
        assertThat(response).
        isNotNull();
        restTemplate.delete("http://localhost:" + port + "/faculty/" + faculty.getId());
    }

    @Test
    public void testPutFaculty() throws Exception {
        Faculty f = new Faculty();
        f.setName("test");
        f.setColor("color");
        Faculty faculty = restTemplate.postForObject("http://localhost:" + port + "/faculty", f, Faculty.class);
        ResponseEntity response = this.restTemplate.exchange("http://localhost:" + port + "/faculty",
        HttpMethod.PUT, new HttpEntity<>(faculty), String.class);
        Assertions.
        assertThat(response).
        isNotNull();
        restTemplate.delete("http://localhost:" + port + "/faculty/" + faculty.getId());
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Faculty f = new Faculty();
        f.setName("test");
        f.setColor("color");
        Faculty faculty = restTemplate.postForObject("http://localhost:" + port + "/faculty", f, Faculty.class);
        restTemplate.delete("http://localhost:" + port + "/faculty/" + faculty.getId());
        ResponseEntity<Faculty> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/" + faculty.getId(), Faculty.class);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    public void testGetStudentsFaculty() throws Exception {
        Faculty f = new Faculty();
        f.setName("test");
        f.setColor("color");
        Faculty faculty = restTemplate.postForObject("http://localhost:" + port + "/faculty", f, Faculty.class);
        Student s = new Student();
        s.setName("name");
        s.setAge(1);
        s.setFaculty(faculty);
        Student student = restTemplate.postForObject("http://localhost:" + port + "/student", s, Student.class);
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/faculty/students/" + student.getId(), String.class);
        Assertions.
        assertThat(response).
        isNotNull(); //Не null потому что в ответе хэдеры, если правильно понимаю концепт
        restTemplate.delete("http://localhost:" + port + "/faculty/" + faculty.getId());
        restTemplate.delete("http://localhost:" + port + "/student/" + student.getId());
    }
}
