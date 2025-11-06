package com.primerparcial.demo.services;

import com.primerparcial.demo.model.Mochila;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MochilaService {

    public List<Mochila> mochilas = new ArrayList<>();

    public MochilaService() {
        Mochila m1 = new Mochila(1, "Nike", 25, true);
        Mochila m2 = new Mochila(2, "Adidas", 30, false);
        Mochila m3 = new Mochila(3, "Puma", 28, true);

        mochilas.add(m1);
        mochilas.add(m2);
        mochilas.add(m3);
    }

    public Mochila getMochilaById(int id) {
        return this.mochilas
                .stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
