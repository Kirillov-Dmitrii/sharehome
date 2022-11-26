package com.skypro.sharehome.controller;

import com.skypro.sharehome.entity.Animal;
import com.skypro.sharehome.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Animal> getAnimalById (@PathVariable Long id){
        Animal animal= animalService.findAnimal(id);
        if(animal == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
    }

    @PostMapping
    public Animal createAnimal (@RequestBody Animal animal){
        return animalService.createAnimal(animal);
    }
}
