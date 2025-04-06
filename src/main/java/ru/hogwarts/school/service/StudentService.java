package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findStudent(long id) {
        return studentRepository.findById(id);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    } 

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Collection<Student>> findByFacultyId (long id) {
        return studentRepository.findByFacultyId(id);
    }

    public Collection<Student> findByAgeBetween(int min, int max){
        return studentRepository.findByAgeBetween(min, max);
    }
}