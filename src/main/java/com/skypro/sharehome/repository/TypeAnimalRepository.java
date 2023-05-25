package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.TypeAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAnimalRepository extends JpaRepository<TypeAnimal, Long> {

    TypeAnimal getTypeAnimalByCode(String code);

    @Query(value = "SELECT id FROM type_animal WHERE CODE = :code", nativeQuery = true)
    Integer findTypeAnimalByCode(@Param("code") String code);
}
