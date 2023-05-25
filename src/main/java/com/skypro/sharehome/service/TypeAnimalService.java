package com.skypro.sharehome.service;

import com.skypro.sharehome.entity.TypeAnimal;
import com.skypro.sharehome.repository.TypeAnimalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TypeAnimalService {

    private final Logger logger = LoggerFactory.getLogger(ShareHomeService.class);

    public final TypeAnimalRepository typeAnimalRepository;

    public TypeAnimalService(TypeAnimalRepository typeAnimalRepository) {
        this.typeAnimalRepository = typeAnimalRepository;
    }

    public Integer getTypeAnimal(String code) {
        logger.info("Was invoked method for get type animal by code");
        return typeAnimalRepository.findTypeAnimalByCode(code);
    }

    public TypeAnimal getTypeAnimalByCode(String code) {
        return typeAnimalRepository.getTypeAnimalByCode(code);
    }
}
