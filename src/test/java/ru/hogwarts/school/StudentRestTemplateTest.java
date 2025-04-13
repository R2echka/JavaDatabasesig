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

import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class StudentRestTemplateTest {
    @LocalServerPort
    private int port;
    
    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setName("test");
        student.setAge(1);
        Student response = restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class);
        Assertions.
        assertThat(response).
        isNotNull();

        restTemplate.delete("http://localhost:" + port + "/student/" + response.getId());
    }

    @Test
    public void testGetStudent() throws Exception {
        Student s = new Student();
        s.setName("test");
        s.setAge(1);
        Student student = restTemplate.postForObject("http://localhost:" + port + "/student", s, Student.class);
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/student/" + student.getId(), String.class);
        Assertions.
        assertThat(response).
        isNotNull();
        restTemplate.delete("http://localhost:" + port + "/student/" + student.getId());
    }

    @Test
    public void testGetStudents() throws Exception {
        Student s = new Student();
        s.setName("test");
        s.setAge(1);
        Student student = restTemplate.postForObject("http://localhost:" + port + "/student", s, Student.class);
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class);
        Assertions.
        assertThat(response).
        isNotNull();
        restTemplate.delete("http://localhost:" + port + "/student/" + student.getId());
    }

    @Test
    public void testPutStudent() throws Exception {
        Student s = new Student();
        s.setName("test");
        s.setAge(1);
        Student student = restTemplate.postForObject("http://localhost:" + port + "/student", s, Student.class);
        ResponseEntity response = this.restTemplate.exchange("http://localhost:" + port + "/student",
        HttpMethod.PUT, new HttpEntity<>(student), String.class);
        Assertions.
        assertThat(response).
        isNotNull();
        restTemplate.delete("http://localhost:" + port + "/student/" + student.getId());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student s = new Student();
        s.setName("test");
        s.setAge(1);
        Student student = restTemplate.postForObject("http://localhost:" + port + "/student", s, Student.class);
        restTemplate.delete("http://localhost:" + port + "/student/" + student.getId());
        ResponseEntity<Student> response = restTemplate.getForEntity("http://localhost:" + port + "/student/" + student.getId(), Student.class);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    public void testGetStudentsFaculty() throws Exception {
        Faculty f = new Faculty();
        f.setName("name");
        f.setColor("color");
        Faculty faculty = restTemplate.postForObject("http://localhost:" + port + "/faculty", f, Faculty.class);
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty/" + faculty.getId(), String.class);
        Assertions.
        assertThat(response).
        isNull();
        restTemplate.delete("http://localhost:" + port + "/faculty/" + faculty.getId());
    }
}
