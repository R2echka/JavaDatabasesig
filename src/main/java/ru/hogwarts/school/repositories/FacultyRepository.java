package ru.hogwarts.school.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.hogwarts.school.model.Faculty;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Optional<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
    Optional<Faculty> findByStudentsId(long id);

}
