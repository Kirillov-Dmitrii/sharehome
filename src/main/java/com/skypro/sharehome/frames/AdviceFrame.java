package com.skypro.sharehome.frames;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;

public class AdviceFrame implements Frame {
    private String messageText = "Вводная информация Рекомендации по:";

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public BaseRequest init(Update update) {

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Транспортировка питомца").callbackData(
                                "TRANSPORTATION"),
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Обустройство дома для щенка").callbackData("EQUIPMENT_PUPPY")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Обустройству дома для взрослой собаки").callbackData("EQUIPMENT_DOG"),
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Обустройству дома для собаки с ограниченными возможностями").callbackData("EQUIPMENT_DOG_DISABLED"),
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Советы и контакты кинолога").callbackData("CYNOLOGIST")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Назад").callbackData("BACK")
                });

        EditMessageText editMessageText = new EditMessageText(update.callbackQuery().message().chat().id(),
                update.callbackQuery().message().messageId(), messageText);

        return editMessageText.replyMarkup(inlineKeyboard);
    }
}
