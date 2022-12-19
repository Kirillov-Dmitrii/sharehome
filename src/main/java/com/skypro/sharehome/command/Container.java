package com.skypro.sharehome.command;

import com.skypro.sharehome.service.ShareHomeService;

import java.util.HashMap;
import java.util.Map;

public class Container {

    private Map<String, Object> map = new HashMap<>();
    private ShareHomeService shareHomeService;

    public Container() {
        map.put(CallbackDataNames.INFO.getCommandName(),
                shareHomeService.getAboutShareHome(shareHomeService.findShareHomeByType("DOG")));
        map.put(CallbackDataNames.TIMETABLE.getCommandName(),
                shareHomeService.getDetailsShareHome(shareHomeService.findShareHomeByType("DOG")));
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
