package ru.hogwarts.school.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repositories.AvatarRepository;

@Service
public class AvatarService {
    
    @Autowired
    private AvatarRepository avatarRepository;

    public Collection<Avatar> getAllAvatars(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
