package com.skypro.sharehome.frames;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;

public interface Frame {
    public BaseRequest init(Update update);
}
