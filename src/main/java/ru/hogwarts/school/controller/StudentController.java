package ru.hogwarts.school.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Student>> getStudentInfo(@PathVariable Long id) {
        Optional<Student> student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/faculty/{id}")
    public ResponseEntity<Collection<Student>> getStudentsFaculty(@PathVariable Long id) {
        Collection<Student> students = studentService.findByFacultyId(id);
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudents(@RequestParam(required=false) Integer min, @RequestParam(required=false) Integer max) {
        if (min != null && max != null) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/count")
    public Integer countStudents() {
        return studentService.countStudents();
    }

    @GetMapping("/avg-age")
    public Double getAvgAge() {
        return studentService.getAvgAge();
    }

    @GetMapping("/last-five")
    public List<Student> getLastStudents() {
        return studentService.getLastStudents();
    }

    @GetMapping("/a-starting-names")
    public List<String> getNamesByFirstLetter() {
        return studentService.getNamesByFirstLetter();
    }
}