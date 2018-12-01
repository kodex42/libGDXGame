package com.mygdx.game.events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.Entity;

public abstract class Event {

    protected EventType type;
    protected boolean triggered;

    public Event(EventType type) {
        this.type = type;
        this.triggered = false;
    }

    protected abstract void display(DisplayType type);

    public abstract void trigger(Entity entity);

    protected void start() {
        this.triggered = true;
    }

    public void finish() {
        this.triggered = false;
    }
}
