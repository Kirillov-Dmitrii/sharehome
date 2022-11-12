package com.skypro.sharehome.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.sharehome.service.ShareHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ShareHomeUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(ShareHomeUpdatesListener.class);

    private final ShareHomeService shareHomeService;

    @Autowired
    private TelegramBot shareHomeBot;

    public ShareHomeUpdatesListener(ShareHomeService shareHomeService) {
        this.shareHomeService = shareHomeService;
    }

    @PostConstruct
    public void init() {
        shareHomeBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            Long chatId = update.message().chat().id();

            if (update.message().text().equals("/start") ) {
                SendResponse response = shareHomeBot.execute(new SendMessage(chatId, "Здравствуй, путник!"));
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


}
