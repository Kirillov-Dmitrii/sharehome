package com.skypro.sharehome.service;

import com.skypro.sharehome.repository.ShareHomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShareHomeService {

    private final Logger logger = LoggerFactory.getLogger(ShareHomeService.class);

    private final ShareHomeRepository notificationTaskRepository;

    public ShareHomeService(ShareHomeRepository shareHomeRepository) {
        this.notificationTaskRepository = shareHomeRepository;
    }
}
