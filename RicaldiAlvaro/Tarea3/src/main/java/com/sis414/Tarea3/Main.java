package com.sis414.Tarea3;

import org.springframework.web.bind.annotation.GetMapping;

public class Main {
    @GetMapping("/")
    String hello()
    {
        return "Hola Mundo";
    }
}
