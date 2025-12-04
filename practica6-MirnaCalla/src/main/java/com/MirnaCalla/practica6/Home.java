package com.MirnaCalla.practica6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
    @GetMapping("/")
    String hello()
    {
        return "Bienvenido a SIS 414 G2 v2";
    }

    /*
    @GetMapping("/{param}")
    String helloparam(@PathVariable String param)
    {
        return "Bienvenido a SIS 414 G2 v2 " + param;
    }
    */
}