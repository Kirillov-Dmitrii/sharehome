package com.skypro.sharehome.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
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
        System.out.println(updates.toString());
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.callbackQuery() != null) {
                switch (update.callbackQuery().data()) {
                    case "INFO":

                        EditMessageText editMessageText = new EditMessageText(update.callbackQuery().message().chat().id(),
                                update.callbackQuery().message().messageId(), "Хороший приют!Все няш-мяш!");

                        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                                new InlineKeyboardButton[]{
                                        new InlineKeyboardButton("Расписание, адрес, схема проезда").callbackData(
                                                "TIMETABLE"),
                                },
                                new InlineKeyboardButton[]{
                                        new InlineKeyboardButton("Правила посещения").callbackData("RULES_VISIT")
                                },
                                new InlineKeyboardButton[]{
                                        new InlineKeyboardButton("Информация о приюте").callbackData("INFO"),
                                },
                                new InlineKeyboardButton[]{
                                        new InlineKeyboardButton("Позвать волонтера").callbackData("LINK_VOLUNTEER")
                                },
                                new InlineKeyboardButton[]{
                                        new InlineKeyboardButton("В меню").callbackData("BACK_MENU")
                                });

                        editMessageText.replyMarkup(inlineKeyboard);

                        BaseResponse response = shareHomeBot.execute(editMessageText);

                        break;
                    case "GET_PET":
                        System.out.println("get_pet");
                        break;
                    case "PET_REPLY":
                        System.out.println("pet_reply");
                        break;
                    case "LINK_VOLUNTEER":
                        System.out.println("link_volunteer");
                }

            } else {

                if (update.message().text().equals("/start") ) {
                    Long chatId = update.message().chat().id();
                    SendMessage message = new SendMessage(chatId, "Здравствуй, путник!");

                    InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                            new InlineKeyboardButton[]{
                                    new InlineKeyboardButton("Информация о приюте").callbackData("INFO"),
                            },
                            new InlineKeyboardButton[]{
                                    new InlineKeyboardButton("Как взять питомца").callbackData("GET_PET")
                            },
                            new InlineKeyboardButton[]{
                                    new InlineKeyboardButton("Прислать отчет о питомце").callbackData("PET_REPLY")
                            },
                            new InlineKeyboardButton[]{
                                    new InlineKeyboardButton("Позвать волонтера").callbackData("LINK_VOLUNTEER")
                            });

                    message.replyMarkup(inlineKeyboard);
                    SendResponse response = shareHomeBot.execute(message);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


}
