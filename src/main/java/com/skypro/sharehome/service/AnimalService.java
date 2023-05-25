package com.skypro.sharehome.service;

import com.skypro.sharehome.entity.Animal;
import com.skypro.sharehome.repository.AnimalRepository;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal addAnimal(Animal animal){
        return animalRepository.save(animal);
    }

    public Animal findAnimal(long id){
        return animalRepository.findById(id).orElse(null);
    }

    public Animal updateAnimal(Animal animal){
        return animalRepository.save(animal);
    }

}
