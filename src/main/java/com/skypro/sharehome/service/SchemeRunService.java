package com.skypro.sharehome.service;

import com.skypro.sharehome.entity.SchemeRun;
import com.skypro.sharehome.entity.ShareHome;
import com.skypro.sharehome.repository.SchemeRunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SchemeRunService {

    private final Logger logger = LoggerFactory.getLogger(ShareHomeService.class);

    private final SchemeRunRepository schemeRunRepository;

    public SchemeRunService(SchemeRunRepository schemeRunRepository) {
        this.schemeRunRepository = schemeRunRepository;
    }

    public SchemeRun getSchemeRun(ShareHome shareHome) {
        logger.info("Was invoked method for get shedule by shareHome");
        return schemeRunRepository.findSchemeRunByShareHome(shareHome.getId());
    }
}
