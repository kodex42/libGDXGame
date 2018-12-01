package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.world.GameMap;

public abstract class Entity {

    protected Vector2 pos;
    protected EntityType type;
    protected GameMap map;
    protected boolean collidable;
    protected boolean fightable;

    public Entity(float x, float y, EntityType type, GameMap map, boolean isCollidable, boolean fightable) {
        this.pos = new Vector2(x, y);
        this.type = type;
        this.map = map;
        this.collidable = isCollidable;
        this.fightable = fightable;
    }

    public abstract void render (SpriteBatch batch);

    protected void moveX(float amount) {
        float newX = this.pos.x + amount;
        if (!map.doesRectCollide(newX, pos.y, getWidth(), getHeight()/2))
            this.pos.x = newX;
    }

    protected void moveY(float amount) {
        float newY = this.pos.y + amount;
        if (!map.doesRectCollide(pos.x, newY, getWidth(), getHeight()/2))
            this.pos.y = newY;
    }

    public void update (float deltaTime) { }

    public void interaction() { }

    public Vector2 getPos() {
        return pos;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public EntityType getType() {
        return type;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public int getWidth() {
        return type.getWidth();
    }

    public int getHeight() {
        return type.getHeight();
    }

    public float getWeight() {
        return type.getWeight();
    }

    public boolean willFight() {
        return this.fightable;
    }
}
