package com.example.demo2.services;

import com.example.demo2.models.Mesa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MesaService {

    public Mesa mesaDefault = new Mesa("01", "Terraza", 4);
    public List<Mesa> mesas = new ArrayList<>();

    public MesaService() {
        Mesa mesa1 = new Mesa("01", "Terraza", 4);
        Mesa mesa2 = new Mesa("02", "Salón Principal", 6);
        Mesa mesa3 = new Mesa("03", "VIP", 8);
        mesas.add(mesa1);
        mesas.add(mesa2);
        mesas.add(mesa3);
    }

    public Mesa getMesa(Long id) {
        return this.mesas
                .stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}