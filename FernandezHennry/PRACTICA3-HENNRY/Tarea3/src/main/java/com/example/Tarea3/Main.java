package com.example.Tarea3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
    @GetMapping("/")
    String hello()
    {
        return "Hola Mundooooooooooo";
    }
}

