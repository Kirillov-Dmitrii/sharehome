package com.skypro.sharehome.service;

import com.skypro.sharehome.entity.*;
import com.skypro.sharehome.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShareHomeService {

    private final Logger logger = LoggerFactory.getLogger(ShareHomeService.class);

    private final ShareHomeRepository shareHomeRepository;
    private final ClientRepository clientRepository;
    private final ReportRepository reportRepository;
    private final AnimalRepository animalRepository;
    private final SheduleService sheduleService;
    private final TypeAnimalService typeAnimalService;
    private final SchemeRunService schemeRunService;

    public ShareHomeService(ShareHomeRepository shareHomeRepository, SheduleService sheduleService, SchemeRunRepository schemeRunRepository, RefInfoRepository refInfoRepository, ClientRepository clientRepository, ReportRepository reportRepository, AnimalRepository animalRepository, TypeAnimalService typeAnimalService, SchemeRunService schemeRunService) {
        this.shareHomeRepository = shareHomeRepository;
        this.sheduleService = sheduleService;
        this.clientRepository = clientRepository;
        this.reportRepository = reportRepository;
        this.animalRepository = animalRepository;
        this.typeAnimalService = typeAnimalService;
        this.schemeRunService = schemeRunService;
    }

    public ShareHome addShareHome(String nameShareHome, String addressShareHome, String typeAnimal, String security) {
        logger.info("Was invoked method for create sharehome");
        ShareHome shareHome = new ShareHome(null, nameShareHome, typeAnimalService.getTypeAnimalByCode(typeAnimal), addressShareHome, security);
        return shareHomeRepository.save(shareHome);
    }

    public ShareHome findShareHomeByType(String typeCode) {
        logger.info("Was invoked method for search ShareHome");
        return shareHomeRepository.getShareHomeByTypeAnimal(typeAnimalService.getTypeAnimal(typeCode));
    }

    public String getAboutShareHome(ShareHome shareHome){
        logger.info("Was invoked method for getting info about ShareHome");
        return "Приют "
                + shareHomeRepository.getNameShareHome(shareHome.getId()) + "\n"
                + shareHomeRepository.getDescriptionShareHome(shareHome.getId());
    }

    public String getDetailsShareHome(ShareHome shareHome) {
        logger.info("Was invoked method for getting details info about ShareHome");
        return "Расписание работы:\n"
                + sheduleService.getShedules(shareHome)
                + "\n Адрес: "
                + shareHomeRepository.getAddressShareHome(shareHome.getId())
                + "\n Схема проезда: \n"
                + schemeRunService.getSchemeRun(shareHome);
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


    public void deleteShareHome(Long id) {
        logger.info("Was invoked method for delete shareHome");
        logger.debug("We deleting shareHome with id = {}", id);
        shareHomeRepository.deleteById(id);
    }

    public String getInfoSecurity(ShareHome shareHome) {
        logger.info("Was invoked method for get data of security");
        return shareHomeRepository.getSecurityShareHome(shareHome.getId());
    }
}
