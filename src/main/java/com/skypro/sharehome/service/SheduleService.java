package com.skypro.sharehome.service;

import com.skypro.sharehome.entity.ShareHome;
import com.skypro.sharehome.entity.Shedule;
import com.skypro.sharehome.repository.SheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.Collection;

@Service
public class SheduleService {

    private final Logger logger = LoggerFactory.getLogger(ShareHomeService.class);

    private final SheduleRepository sheduleRepository;

    public SheduleService(SheduleRepository sheduleRepository) {
        this.sheduleRepository = sheduleRepository;
    }

    public Collection<String> getShedules(ShareHome shareHome) {
        logger.info("Was invoked method for get shedule by shareHome");
        return sheduleRepository.getShedulesByShareHome(shareHome.getId());
    }


}
