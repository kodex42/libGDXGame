package com.mygdx.game.events;

public enum DisplayType {

    BATTLE("Battle"),
    DIALOG("Dialog"),
    OPTION("Option");

    private final String id;

    DisplayType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
