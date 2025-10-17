package com.example.Practica5.services;

import com.example.Practica5.models.Pillow;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PillowService {

    public List<Pillow> pillows = new ArrayList<>();

    public PillowService(){

        pillows.add(new Pillow(1, "http://localhost:8080/images/white-pillow.webp", "Blanco", 150.8, 80,40 ));
        pillows.add(new Pillow(2, "http://localhost:8080/images/gray-pillow.webp", "Gris", 240, 120,55));
        pillows.add(new Pillow(3, "http://localhost:8080/images/sky-blue-pillow.webp", "Celeste", 180,90,45));
        pillows.add(new Pillow(4, "http://localhost:8080/images/black-pillow.webp", "Negro", 220,100,50));
        pillows.add(new Pillow(5, "http://localhost:8080/images/green-pillow.webp", "Blanco", 150,110,65));

    }

    public List<Pillow> getPillows(){
        return pillows;
    }

    public Optional<Pillow> getPillowByCode(int code) {
        return pillows.stream()
                .filter(pillow -> pillow.getPillowCode() == code)
                .findFirst();
    }

    public Pillow createPillow( Pillow pillow){
        pillows.add(pillow);
        return pillow;
    }

    public boolean updatePillow(int code, Pillow pillow){
        for (Pillow item : pillows) {
            if (item.getPillowCode() == code) {
                item.setImage(pillow.getImage());
                item.setColor(pillow.getColor());
                item.setPrice(pillow.getPrice());
                item.setLength(pillow.getLength());
                item.setWidth(pillow.getWidth());
                return true;
            }
        }
        return false;
    }

    public boolean partiallyUpdatePillow(int code, Pillow pillow){
        for (Pillow item : pillows) {
            if (item.getPillowCode() == code) {
                if (pillow.getImage() != null)
                    item.setImage(pillow.getImage());
                if (pillow.getColor() != null)
                    item.setColor(pillow.getColor());
                if (pillow.getPrice() != 0)
                    item.setPrice(pillow.getPrice());
                if (pillow.getLength() != 0)
                    item.setLength(pillow.getLength());
                if (pillow.getWidth() != 0)
                    item.setWidth(pillow.getWidth());
                return true;
            }
        }
        return false;
    }

    public boolean deletePillow(int code){
        return pillows.removeIf(item -> item.getPillowCode()== code);
    }



}
