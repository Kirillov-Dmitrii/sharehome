package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal getAnimalByName(String name);
}
