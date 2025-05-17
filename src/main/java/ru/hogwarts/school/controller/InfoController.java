package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class InfoController {
    
    @Value("${server.port}")
    private int port;

    @GetMapping("/port")
    public int getServerPort() {
        return port;
    }
    
}
