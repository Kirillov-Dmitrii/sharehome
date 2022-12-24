package com.skypro.sharehome.command;

public enum CallbackDataNames {
    INFO("INFO"),
    TIMETABLE("TIMETABLE"),
    GET_PET("GET_PET"),
    BACK("BACK"),
    PET_REPLY(""),
    LINK_VOLUNTEER("PET_REPLY"),
    BACK_MENU("BACK_MENU"),
    ADVICE("ADVICE"),
    RULES_VISIT("RULES_VISIT"),
    DATING_RULES("DATING_RULES"),
    DOCUMENTS("DOCUMENTS"),
    FAILURE("FAILURE"),
    TRANSPORTATION("TRANSPORTATION"),
    EQUIPMENT_PUPPY("EQUIPMENT_PUPPY"),
    EQUIPMENT_DOG("EQUIPMENT_DOG"),
    EQUIPMENT_DOG_DISABLED("EQUIPMENT_DOG_DISABLED"),
    CYNOLOGIST("CYNOLOGIST"),
    DOG("DOG"),
    CAT("CAT");


    private final String commandName;

    CallbackDataNames(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
