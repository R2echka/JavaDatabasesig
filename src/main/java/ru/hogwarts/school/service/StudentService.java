package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        logger.info("Was invoked method for creating new student");
        return studentRepository.save(student);
    }

    public Optional<Student> findStudent(long id) {
        logger.info("Was invoked method for getting a student");
        return studentRepository.findById(id);
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for editing a student");
        return studentRepository.save(student);
    } 

    public void deleteStudent(long id) {
        logger.info("Was invoked method for deleting a student");
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudents(){
        logger.info("Was invoked method for getting all students");
        return studentRepository.findAll();
    }

    public Collection<Student> findByFacultyId (long id) {
        logger.info("Was invoked method for getting a student by faculty id");
        return studentRepository.findByFacultyId(id);
    }

    public Collection<Student> findByAgeBetween(int min, int max){
        logger.info("Was invoked method for getting student by age gap");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Integer countStudents(){
        logger.info("Was invoked method for getting number of students");
        return studentRepository.countStudents();
    }

    public Double getAvgAge(){
        logger.info("Was invoked method for getting avg students age");
        // return studentRepository.getAvgAge();
        return studentRepository.findAll()
        .stream()
        .collect(Collectors.averagingDouble(Student::getAge));
    }

    public List<Student> getLastStudents(){
        logger.info("Was invoked method for getting last 5 students");
        return studentRepository.getLastStudents();
    }

    public List<String> getNamesByFirstLetter(){
        return studentRepository.findAll()
        .stream()
        .parallel()
        .map(Student::getName)
        .map(String::toUpperCase)
        .filter(name -> name.startsWith("А"))
        .sorted()
        .collect(Collectors.toList());
    }

    public synchronized void printName(int id){
        List<Student> students = studentRepository.findAll();
        if (students.size() < 6) {
            Thread.currentThread().interrupt();
        }
        if (!Thread.interrupted()){
            System.out.println(students.get(id).getName());
        }
    }
}