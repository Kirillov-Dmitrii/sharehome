package com.skypro.sharehome.frames;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;
import org.springframework.stereotype.Service;

@Service
public interface Frame {
    public BaseRequest init(Update update);
}
