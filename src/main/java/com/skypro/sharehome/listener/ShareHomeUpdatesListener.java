package com.skypro.sharehome.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.sharehome.command.CallbackDataNames;
import com.skypro.sharehome.command.KindOfPet;
import com.skypro.sharehome.entity.Client;
import com.skypro.sharehome.frames.*;
import com.skypro.sharehome.service.ClientService;
import com.skypro.sharehome.service.RefInfoService;
import com.skypro.sharehome.service.ShareHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ShareHomeUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(ShareHomeUpdatesListener.class);

    private final ShareHomeService shareHomeService;
    private final RefInfoService refInfoService;
    private final ClientService clientService;
    private final MainMenuFrame mainMenuFrame = new MainMenuFrame();
    private final InfoFrame infoFrame = new InfoFrame();
    private final GetPetFrame getPetFrame = new GetPetFrame();
    private final AdviceFrame adviceFrame = new AdviceFrame();
    private final PetReplyFrame petReplyFrame = new PetReplyFrame();
    private final Frame welcomeFrame = new WelcomeFrame();

    private String typeShareHome = "DOG";


    @Autowired
    private TelegramBot shareHomeBot;

    public ShareHomeUpdatesListener(ShareHomeService shareHomeService, RefInfoService refInfoService, ClientService clientService) {
        this.shareHomeService = shareHomeService;
        this.refInfoService = refInfoService;
        this.clientService = clientService;
    }

    @PostConstruct
    public void init() {
        shareHomeBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        System.out.println(updates.toString());
        updates.forEach(update -> {
            logger.info("Processing  update: {}", update);

            //Этот блок кода выполняет тот же фунционал, что и switch
            //Он работает, но пока его не трогаем
//            container.init();
//
//            if (update.callbackQuery() != null && update.callbackQuery().data().equals("DOG")) {
//                shareHomeBot.execute(mainMenuFrame.init(update));
//            }
////
//            if (update.callbackQuery() != null) {
//                if (container.getMap().containsKey(update.callbackQuery().data()) && !update.callbackQuery().data().equals("/start")) {
//                    infoFrame.setMessageText(container.getMap().get(update.callbackQuery().data()));
//                    shareHomeBot.execute(infoFrame.init(update));
//                }
//            } else {
//
//                if (update.message() != null && update.message().text().equals("/start")) {
//                    shareHomeBot.execute(welcomeFrame.init(update));
//                }
//            }

            if (update.callbackQuery() != null) {
                //Подогнать контейнер и по ключу проверять входное сообщение
                switch (update.callbackQuery().data()) {
                    case "DOG":
                    case "CAT":
                        KindOfPet.pet = update.callbackQuery().data();
                        shareHomeBot.execute(mainMenuFrame.init(update));
                        System.out.println(KindOfPet.pet);
                        break;
                    case "INFO":
                        infoFrame.setMessageText(shareHomeService.getAboutShareHome(shareHomeService.findShareHomeByType(KindOfPet.pet)));
                        shareHomeBot.execute(infoFrame.init(update));
                        break;
                    case "TIMETABLE":
                        //infoFrame.setMessageText("Расписание, адрес, схема проезда");
                        infoFrame.setMessageText(shareHomeService.getDetailsShareHome(shareHomeService.findShareHomeByType(KindOfPet.pet)));
                        shareHomeBot.execute(infoFrame.init(update));
                        break;
                    case "GET_PET":
                    case "BACK":
                        shareHomeBot.execute(getPetFrame.init(update));
                        break;
                    case "PET_REPLY":
                        shareHomeBot.execute(petReplyFrame.init(update));
                        break;
                    case "LINK_VOLUNTEER":
                        System.out.println("link_volunteer");
                        break;
                    case "BACK_MENU":
                        shareHomeBot.execute(mainMenuFrame.init(update));
                        break;
                    case "ADVICE":
                        shareHomeBot.execute(adviceFrame.init(update));
                        break;
                    case "RULES_VISIT":
                        infoFrame.setMessageText(refInfoService.getDocument(shareHomeService.findShareHomeByType(typeShareHome), "RULES_VISIT")); //Правила посещения приюта
                        shareHomeBot.execute(infoFrame.init(update));
                        break;
                    case "DATING_RULES":
                        getPetFrame.setMessageText(refInfoService.getDocument(shareHomeService.findShareHomeByType(typeShareHome), "DATING_RULES")); //Правила знакомства с питомцем
                        shareHomeBot.execute(getPetFrame.init(update));
                        break;
                    case "DOCUMENTS":
                        getPetFrame.setMessageText(refInfoService.getDocument(shareHomeService.findShareHomeByType(typeShareHome), "DOCUMENTS")); //Список документов
                        shareHomeBot.execute(getPetFrame.init(update));
                        break;
                    case "FAILURE":
                        getPetFrame.setMessageText(refInfoService.getDocument(shareHomeService.findShareHomeByType(typeShareHome), "FAILURE")); //Причины отказа
                        shareHomeBot.execute(getPetFrame.init(update));
                        break;
                    case "TRANSPORTATION":
                        adviceFrame.setMessageText(refInfoService.getDocument(shareHomeService.findShareHomeByType(typeShareHome), "TRANSPORTATION")); //Список рекомендаций по транспортировке
                        shareHomeBot.execute(adviceFrame.init(update));
                        break;
                    case "EQUIPMENT_PUPPY":
                        adviceFrame.setMessageText(refInfoService.getDocument(shareHomeService.findShareHomeByType(typeShareHome), "EQUIPMENT_PUPPY")); //Обустройство дома для щенка
                        shareHomeBot.execute(adviceFrame.init(update));
                        break;
                    case "EQUIPMENT_DOG":
                        adviceFrame.setMessageText(refInfoService.getDocument(shareHomeService.findShareHomeByType(typeShareHome), "EQUIPMENT_DOG")); //Обустройство дома для взрослого животного
                        shareHomeBot.execute(adviceFrame.init(update));
                        break;
                    case "EQUIPMENT_DOG_DISABLED":
                        adviceFrame.setMessageText(refInfoService.getDocument(shareHomeService.findShareHomeByType(typeShareHome), "EQUIPMENT_DOG_DISABLED")); //Обустройство дома для животного с ограниченными возможностями
                        shareHomeBot.execute(adviceFrame.init(update));
                        break;
                    case "CYNOLOGIST":
                        if (typeShareHome.equals("DOG")){
                            adviceFrame.setMessageText(refInfoService.getDocument(shareHomeService.findShareHomeByType(typeShareHome), "CYNOLOGIST")); //Советы кинолога
                        } else adviceFrame.setMessageText("Советы кинолога доступны только для приюта собак");

                        shareHomeBot.execute(adviceFrame.init(update));
                        break;
                    case "CYNOLOGIST_LIST":
                        if (typeShareHome.equals("DOG")){
                            adviceFrame.setMessageText(refInfoService.getDocument(shareHomeService.findShareHomeByType(typeShareHome), "CYNOLOGIST_LIST")); //Список проверенных кинологов
                        } else adviceFrame.setMessageText("Список кинологов доступен только для приюта собак");
                        shareHomeBot.execute(adviceFrame.init(update));
                        break;
                    case "WRITE_PHONE":
                        Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
                        Matcher matcher = pattern.matcher(update.message().text());
                        String name = matcher.group(1);
                        String phone = matcher.group(3);
                        infoFrame.setMessageText(clientService.addPhoneOfClient(shareHomeService.findShareHomeByType(typeShareHome), update.message().chat().id(), name, phone)); //принять и записать контактные данные для связи
                        shareHomeBot.execute(adviceFrame.init(update));
                        break;
                    case "GET_SECURITY":
                        infoFrame.setMessageText(shareHomeService.getInfoSecurity(shareHomeService.findShareHomeByType(typeShareHome))); //принять и записать контактные данные для связи
                        shareHomeBot.execute(adviceFrame.init(update));
                        break;

                }

            } else {

                if (update.message() != null && update.message().text().equals("/start")) {

                    shareHomeBot.execute(welcomeFrame.init(update));
                }
            }
        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Scheduled(cron = "@daily")
    public int run() {
        try {
            Integer countNeedDone = 30;
            List<Client> clientList;
            clientList = clientService.getClientsDoneTrialPeriod(countNeedDone);
            clientList
                    .forEach(task -> {
                        Long idChat = task.getIdChat();
                        SendResponse response = shareHomeBot.execute(new SendMessage(idChat, "Поздравляем! Вы прошли испытательный срок!"));
                    } );
        } catch (NullPointerException ignored){
        }
        finally {
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }
    }



}
