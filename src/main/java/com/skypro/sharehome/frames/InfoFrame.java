package com.skypro.sharehome.frames;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;

public class InfoFrame implements Frame {

    private String messageText;

    @Override
    public BaseRequest init(Update update) {

        return createMessage(update, messageText).replyMarkup(createKeyBoard());

    }

    private EditMessageText createMessage(Update update, String text) {

        return new EditMessageText(update.callbackQuery().message().chat().id(),
                update.callbackQuery().message().messageId(), text);
    }


    private InlineKeyboardMarkup createKeyBoard() {
       return new InlineKeyboardMarkup(
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
                         new InlineKeyboardButton("Записать контактные данные для связи").callbackData("WRITE_PHONE")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("В меню").callbackData("BACK_MENU")
                });
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
