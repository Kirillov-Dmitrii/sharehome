package com.skypro.sharehome.service;

import com.skypro.sharehome.entity.Animal;
import com.skypro.sharehome.entity.Client;
import com.skypro.sharehome.entity.Report;
import com.skypro.sharehome.entity.ShareHome;
import com.skypro.sharehome.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShareHomeService {

    private final Logger logger = LoggerFactory.getLogger(ShareHomeService.class);

    private final ShareHomeRepository shareHomeRepository;
    private final SheduleRepository sheduleRepository;
    private final SchemeRunRepository schemeRunRepository;
    private final RefInfoRepository refInfoRepository;
    private final ClientRepository clientRepository;
    private final ReportRepository reportRepository;
    private final AnimalRepository animalRepository;

    public ShareHomeService(ShareHomeRepository shareHomeRepository, SheduleRepository sheduleRepository, SchemeRunRepository schemeRunRepository, RefInfoRepository refInfoRepository, ClientRepository clientRepository, ReportRepository reportRepository, AnimalRepository animalRepository) {
        this.shareHomeRepository = shareHomeRepository;
        this.sheduleRepository = sheduleRepository;
        this.schemeRunRepository = schemeRunRepository;
        this.refInfoRepository = refInfoRepository;
        this.clientRepository = clientRepository;
        this.reportRepository = reportRepository;
        this.animalRepository = animalRepository;
    }

    public String getAboutShareHome(ShareHome shareHome){
        logger.info("Was invoked method for getting info about ShareHome");
        if (shareHome == null){
            shareHome = shareHomeRepository.getReferenceById(1L);
        }
        return "Приют "
                + shareHomeRepository.getNameShareHome(shareHome.getId()) + "\n"
                + shareHomeRepository.getDescriptionShareHome(shareHome.getId());
    }

    public String getDetailsShareHome(ShareHome shareHome) {
        logger.info("Was invoked method for getting details info about ShareHome");
        if (shareHome == null){
            shareHome = shareHomeRepository.getReferenceById(1L);
        }
        return "Расписание работы:\n"
                + sheduleRepository.getShedulesByShareHome(shareHome.getId())
                + "\n Адрес: "
                + shareHomeRepository.getAddressShareHome(shareHome.getId())
                + "\n Схема проезда: \n"
                + schemeRunRepository.findSchemeRunByShareHome(shareHome.getId());
    }

    public String getDocument(ShareHome shareHome, String nameDoc) {
        logger.info("Was invoked method for get document by name from table information");
        if (shareHome == null){
            shareHome = shareHomeRepository.getReferenceById(1L);
        }
        return refInfoRepository.getDescriptionByDocumentName(shareHome.getId(), nameDoc);
    }


    public Report startWithAnimal(Long idChat, String nameAnimal) {
        logger.info("Was invoked method for start getting animal");
        Client client = clientRepository.getClientByIdChat(idChat);
        client.setCountDays(0);
        client.setOwner(true);

        Animal animal = animalRepository.getAnimalByName(nameAnimal);
        animal.setOwner(client);

        clientRepository.save(client);
        animalRepository.save(animal);

        return reportRepository.save(new Report(null, animal, null, null, null, null, null));
    }

    public List<Report> getReport(LocalDate localDate){
        logger.info("Was invoked method for get tasks");
        return reportRepository.findReportByDayReport(localDate);
    }



}
