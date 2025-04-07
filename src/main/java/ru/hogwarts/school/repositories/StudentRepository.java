package ru.hogwarts.school.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.hogwarts.school.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int min, int max);
    Collection<Student> findByFacultyId(long id);

}
