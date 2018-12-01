package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.world.GameMap;

public class Player extends Entity {

    private static final int SPEED = 120;

    Texture image;

    public Player(float x, float y, GameMap map) {
        super(x, y, EntityType.PLAYER, map, false, false);
        image = new Texture("core/assets/player.png");
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            moveX(-SPEED * deltaTime);
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            moveX(SPEED * deltaTime);
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            moveY(-SPEED * deltaTime);
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            moveY(SPEED * deltaTime);
        if (Gdx.input.isKeyPressed(Input.Keys.E))
            check();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }

    private void check() {
        // Check nearby collision before performing an event
    }
}
