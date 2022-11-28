package com.skypro.sharehome.frames;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;

public class MainMenuFrame implements Frame {

    @Override
    public BaseRequest init(Update update) {
        //создаем клавиатуру
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

        if (update.callbackQuery() != null) {
            //изменяем сообщение
            EditMessageText editMessageText = new EditMessageText(update.callbackQuery().message().chat().id(),
                    update.callbackQuery().message().messageId(), "Share_home_bot даст тебе всю необходимую информацию о приюте.");
            return editMessageText.replyMarkup(inlineKeyboard);

        } else {
            Long chatId = update.message().chat().id();
            SendMessage message = new SendMessage(chatId, "Здравствуй, гость! \n" +
                    "Share_home_bot даст тебе всю необходимую информацию о приюте.");
            return message.replyMarkup(inlineKeyboard);
        }
    }
}
