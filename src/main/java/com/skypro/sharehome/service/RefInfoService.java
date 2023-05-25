package com.skypro.sharehome.service;

import com.skypro.sharehome.entity.ShareHome;
import com.skypro.sharehome.repository.RefInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RefInfoService {

    private final Logger logger = LoggerFactory.getLogger(ShareHomeService.class);

    private final RefInfoRepository refInfoRepository;

    public RefInfoService(RefInfoRepository refInfoRepository) {
        this.refInfoRepository = refInfoRepository;
    }

    public String getDocument(ShareHome shareHome, String codeName) {
        logger.info("Was invoked method for get document by name from table information");
        return refInfoRepository.getDescriptionByDocumentName(shareHome.getId(), codeName);
    }

/*
    Перечень документов:
    1. Правила знакомства с животным до того как взять его из приюта ALL
    2. Список документов, для того чтобы взять животное ALL
    3. Список рекомендаций по транспортировке животного ALL
    4. Список рекомендаций по обустройству дома для щенка/котенка ALL
    5. Список рекомендаций по обустройству дома для взрослого животного ALL
    6. Список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение, передвижение) ALL
    7. Рекомендации по проверенным кинологам DOG
    8. Список причин, почему могут отказать и не дать забрать собаку из приюта DOG
*/




}
