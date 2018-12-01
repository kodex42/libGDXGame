package com.mygdx.game.events;

public enum EventType {

    BATTLE("Battle"),
    PICK("Pick Up"),
    TALK("Talk"),
    READ("Read"),
    CHOICE("Choice"),
    CHECK("Check");

    private String id;

    EventType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
