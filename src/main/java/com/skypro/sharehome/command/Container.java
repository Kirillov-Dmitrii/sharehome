package com.skypro.sharehome.command;

import com.skypro.sharehome.service.ShareHomeService;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class Container {

    private Map<String, String> map = new HashMap<>();
    private  final ShareHomeService shareHomeService;


    public Container(ShareHomeService shareHomeService) {
        this.shareHomeService = shareHomeService;
    }

    public void init() {
        map.put(CallbackDataNames.INFO.getCommandName(),
                shareHomeService.getAboutShareHome(shareHomeService.findShareHomeByType("DOG")));
        map.put(CallbackDataNames.TIMETABLE.getCommandName(),
                shareHomeService.getDetailsShareHome(shareHomeService.findShareHomeByType("DOG")));
    }

    public Map<String, String> getMap() {
        return map;
    }
}
