package Siistemas._4.G2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {
    @GetMapping("/")
    String hello()
    {
        return "Hola Mundo";
    }
}
