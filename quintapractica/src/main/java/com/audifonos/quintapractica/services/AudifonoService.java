package com.audifonos.quintapractica.services;

import com.audifonos.quintapractica.models.Audifono;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AudifonoService {

    private final List<Audifono> audifonos = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1);

    // Semilla en el constructor (como tu StudentService)
    public AudifonoService() {
        audifonos.add(new Audifono(sequence.getAndIncrement(), "Sony", "WH-1000XM5", 299.99, true));
        audifonos.add(new Audifono(sequence.getAndIncrement(), "JBL", "Tune 520BT", 59.99, true));
        audifonos.add(new Audifono(sequence.getAndIncrement(), "Audio-Technica", "M50x", 149.00, false));
    }

    public List<Audifono> getAll() {
        return audifonos;
    }


    public Audifono getById(Long id) {
        return audifonos.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findFirst()
                .orElse(null);
    }


    public Audifono create(Audifono nuevo) {
        nuevo.setId(sequence.getAndIncrement());
        audifonos.add(nuevo);
        return nuevo;
    }


    public Audifono replace(Long id, Audifono data) {
        for (int i = 0; i < audifonos.size(); i++) {
            if (Objects.equals(audifonos.get(i).getId(), id)) {
                data.setId(id);
                audifonos.set(i, data);
                return data;
            }
        }
        return null;
    }


    public Audifono patch(Long id, Map<String, Object> cambios) {
        Audifono a = getById(id);
        if (a == null) return null;

        if (cambios.containsKey("marca")) a.setMarca((String) cambios.get("marca"));
        if (cambios.containsKey("modelo")) a.setModelo((String) cambios.get("modelo"));
        if (cambios.containsKey("precio")) {
            Object raw = cambios.get("precio");
            if (raw instanceof Number) a.setPrecio(((Number) raw).doubleValue());
            else if (raw instanceof String) {
                try { a.setPrecio(Double.parseDouble((String) raw)); } catch (Exception ignored) {}
            }
        }
        if (cambios.containsKey("inalambrico")) {
            Object v = cambios.get("inalambrico");
            a.setInalambrico(Boolean.valueOf(String.valueOf(v)));
        }
        return a;
    }


    public boolean delete(Long id) {
        return audifonos.removeIf(a -> Objects.equals(a.getId(), id));
    }
}