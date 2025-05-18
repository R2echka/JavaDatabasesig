package ru.hogwarts.school.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repositories.AvatarRepository;

@Service
public class AvatarService {

    Logger logger = LoggerFactory.getLogger(AvatarService.class);
    
    @Autowired
    private AvatarRepository avatarRepository;

    public Collection<Avatar> getAllAvatars(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        logger.info("Was invoked method for getting all avatars");
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
