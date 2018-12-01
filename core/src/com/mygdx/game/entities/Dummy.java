package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.world.GameMap;

public class Dummy extends Entity {
    private static final int SPEED = 80;

    Texture image;

    public Dummy(float x, float y, GameMap map) {
        super(x, y, EntityType.DUMMY, map, true, true);
        image = new Texture("core/assets/mannequin.png");
    }

    @Override
    public void update(float deltaTime) { }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }

    @Override
    public void interaction() {
        super.interaction();
    }
}
