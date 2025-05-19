package ru.hogwarts.school.controller;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    
    @Value("${server.port}")
    private int port;

    @GetMapping("/port")
    public int getServerPort() {
        return port;
    }
    
    @GetMapping("/sum")
    public long getSum() {
        return Stream
        .iterate(1, a -> a + 1)
        .limit(1_000_000)
        .parallel()
        .reduce(0, (a, b) -> a + b);
    }
}
