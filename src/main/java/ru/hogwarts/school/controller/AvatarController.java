package ru.hogwarts.school.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/avatar")
public class AvatarController {
    
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping()
    public ResponseEntity<Collection<Avatar>> getAllAvatars(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize) {
        return ResponseEntity.ok(avatarService.getAllAvatars(pageNum, pageSize));
    }
}
