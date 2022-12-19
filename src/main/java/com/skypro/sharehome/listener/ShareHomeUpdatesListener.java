package com.skypro.sharehome.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.sharehome.command.Container;
import com.skypro.sharehome.entity.ShareHome;
import com.skypro.sharehome.frames.*;
import com.skypro.sharehome.service.ShareHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ShareHomeUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(ShareHomeUpdatesListener.class);

    private Frame frame;
    private final ShareHomeService shareHomeService;
    private final MainMenuFrame mainMenuFrame = new MainMenuFrame();
    private final InfoFrame infoFrame = new InfoFrame();
    private final GetPetFrame getPetFrame = new GetPetFrame();
    private final AdviceFrame adviceFrame = new AdviceFrame();
    private final PetReplyFrame petReplyFrame = new PetReplyFrame();
    private String typeShareHome = "DOG";

    private Container container;


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
        System.out.println(updates.toString());
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.callbackQuery() != null && !update.message().text().equals("/start")) {
                container.getMap().get(update.callbackQuery().data());
                shareHomeBot.execute(frame.init(update));
            } else {

                if (update.message() != null && update.message().text().equals("/start")) {

                    shareHomeBot.execute(mainMenuFrame.init(update));
                }
            }

//            if (update.callbackQuery() != null) {
//                //Подогнать контейнер и по ключу проверять входное сообщение
//                switch (update.callbackQuery().data()) {
//                    case "INFO":
//                        infoFrame.setMessageText(shareHomeService.getAboutShareHome(shareHomeService.findShareHomeByType(typeShareHome)));
//                        shareHomeBot.execute(infoFrame.init(update));
////
//                        break;
//                    case "TIMETABLE":
//                        //infoFrame.setMessageText("Расписание, адрес, схема проезда");
//                        infoFrame.setMessageText(shareHomeService.getDetailsShareHome(shareHomeService.findShareHomeByType(typeShareHome)));
//                        shareHomeBot.execute(infoFrame.init(update));
//                        break;
//                    case "GET_PET":
//                    case "BACK":
//                        shareHomeBot.execute(getPetFrame.init(update));
//                        break;
//                    case "PET_REPLY":
//                        shareHomeBot.execute(petReplyFrame.init(update));
//                        break;
//                    case "LINK_VOLUNTEER":
//                        System.out.println("link_volunteer");
//                        break;
//                    case "BACK_MENU":
//                        shareHomeBot.execute(mainMenuFrame.init(update));
//                        break;
//                    case "ADVICE":
//                        shareHomeBot.execute(adviceFrame.init(update));
//                        break;
//                    case "RULES_VISIT":
//                        infoFrame.setMessageText("Вся информацио о правилах посещения приюта");
//                        shareHomeBot.execute(infoFrame.init(update));
//                        break;
//                    case "DATING_RULES":
//                        getPetFrame.setMessageText("Правила знакомства с питомцем");
//                        shareHomeBot.execute(getPetFrame.init(update));
//                        break;
//                    case "DOCUMENTS":
//                        getPetFrame.setMessageText("Список документов");
//                        shareHomeBot.execute(getPetFrame.init(update));
//                        break;
//                    case "FAILURE":
//                        getPetFrame.setMessageText("Причины отказа");
//                        shareHomeBot.execute(getPetFrame.init(update));
//                        break;
//                    case "TRANSPORTATION":
//                        adviceFrame.setMessageText("Список документов");
//                        shareHomeBot.execute(adviceFrame.init(update));
//                        break;
//                    case "EQUIPMENT_PUPPY":
//                        adviceFrame.setMessageText("Обустройство дома для щенка");
//                        shareHomeBot.execute(adviceFrame.init(update));
//                        break;
//                    case "EQUIPMENT_DOG":
//                        adviceFrame.setMessageText("Обустройству дома для взрослой собаки");
//                        shareHomeBot.execute(adviceFrame.init(update));
//                        break;
//                    case "EQUIPMENT_DOG_DISABLED":
//                        adviceFrame.setMessageText("Обустройству дома для собаки с ограниченными возможностями");
//                        shareHomeBot.execute(adviceFrame.init(update));
//                        break;
//                    case "CYNOLOGIST":
//                        adviceFrame.setMessageText("Советы и контакты кинолога");
//                        shareHomeBot.execute(adviceFrame.init(update));
//                        break;
//
//                }
//
//            } else {
//
//                if (update.message() != null && update.message().text().equals("/start")) {
//
//                    shareHomeBot.execute(mainMenuFrame.init(update));
//                }
//            }
        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
