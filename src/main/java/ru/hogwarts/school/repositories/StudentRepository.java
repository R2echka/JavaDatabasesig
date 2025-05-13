package ru.hogwarts.school.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.hogwarts.school.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int min, int max);
    Collection<Student> findByFacultyId(long id);

    @Query(value = "SELECT COUNT(*) from student", nativeQuery=true)
    Integer countStudents();

    @Query(value = "SELECT AVG(age) AS avg_age from student", nativeQuery=true)
    Double getAvgAge();

    @Query(value = "SELECT * from student ORDER BY id desc LIMIT 5", nativeQuery=true)
    List<Student> getLastStudents();
}
