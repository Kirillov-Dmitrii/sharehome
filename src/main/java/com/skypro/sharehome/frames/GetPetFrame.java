package com.skypro.sharehome.frames;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;

public class GetPetFrame implements Frame {

    private String messageText = "Краткая инфа по приюту питомцев и рекомендации по " +
            "благоустройству";

    @Override
    public BaseRequest init(Update update) {

        return createMessage(update, messageText).replyMarkup(createKeyboard());

    }

    private EditMessageText createMessage(Update update, String text) {

        return new EditMessageText(update.callbackQuery().message().chat().id(),
                update.callbackQuery().message().messageId(), text);
    }


    private InlineKeyboardMarkup createKeyboard() {

        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Правила знакомства с питомцем").callbackData(
                                "DATING_RULES"),
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Список документов").callbackData("DOCUMENTS")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Рекомендации и советы").callbackData("ADVICE"),
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Причины отказа").callbackData("FAILURE"),
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Позвать волонтера").callbackData("LINK_VOLUNTEER")
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
