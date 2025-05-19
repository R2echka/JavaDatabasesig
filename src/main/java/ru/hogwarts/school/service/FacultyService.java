package ru.hogwarts.school.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for creating new faculty");
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> findFaculty(long id) {
        logger.info("Was invoked method for getting a faculty");
        return facultyRepository.findById(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for editing a faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for deleting a faculty");
        facultyRepository.deleteById(id);
    }

    public Optional<Faculty> findByStudentsId (long id) {
        logger.info("Was invoked method for getting a faculty by student id");
        return facultyRepository.findByStudentsId(id);
    }

    public Optional<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color){
        logger.info("Was invoked method getting a faculty by name or color");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Optional<String> getLongestName(){
        return facultyRepository.findAll()
        .stream()
        .parallel()
        .map(Faculty::getName)
        .max((n1, n2) -> Integer.compare(n1.length(), n2.length()));
    }
}