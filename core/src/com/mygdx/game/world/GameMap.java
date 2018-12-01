package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Dummy;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityType;
import com.mygdx.game.entities.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class GameMap {

    protected ArrayList<Entity> entities;

    public GameMap() {
        this.entities = new ArrayList<>();
        this.entities.add(new Dummy(320-EntityType.DUMMY.getWidth()/2, 416, this));
        this.entities.add(new Player(320-EntityType.PLAYER.getWidth()/2, 32, this));
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
        }
    }

    public void update(float delta) {
        // This sort makes it so entities with a higher y value appear behind others
        Collections.sort(this.entities, new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                return Float.compare(o2.getPos().y, o1.getPos().y);
            }
        });
        for (Entity entity : entities) {
            entity.update(delta);
        }
    }


    public boolean doesRectCollide(float x, float y, int width, int height) {
        if (x < 0 || y < 0 || x + width > getPixelWidth() || y + height > getPixelHeight())
            return true;

        // Check only the tiles that the player's collision box is currently colliding with
        for (int row = (int) y / TileType.TILE_SIZE; row < Math.ceil((y + height) / TileType.TILE_SIZE); row++) {
            for (int col = (int) x / TileType.TILE_SIZE; col < Math.ceil((x + width) / TileType.TILE_SIZE); col++) {
                for (int layer = 0; layer < getLayers(); layer++) {
                    TileType type = getTileTypeByCoordinates(layer, col, row);
                    if (type != null && type.isCollidable())
                        return true;
                }
            }
        }

        for (Entity entity : entities) {
            if (entity.isCollidable()) {
                Vector2 pos = entity.getPos();
                int w = entity.getWidth();
                int h = entity.getHeight();

                // This only returns true if the player box collides with another entity's collision box
                if (((x >= pos.x && x < pos.x+w) || (x+width >= pos.x && x+width < pos.x+w)) && ((y >= pos.y && y < pos.y+h/2) || (y+height >= pos.y && y+height < pos.y+h/2)))
                    return true;
            }

        }

        return false;
    }

    public int getPixelWidth() {
        return this.getWidth() * TileType.TILE_SIZE;
    }

    public int getPixelHeight() {
        return this.getHeight() * TileType.TILE_SIZE;
    }

    /**
     * Gets a tile by pixel position withing the game world at a specified layer
     * @param layerIndex
     * @param x
     * @param y
     * @return
     */
    public TileType getTileTypeByLocation(int layerIndex, float x, float y) {
        return this.getTileTypeByCoordinates(layerIndex, (int) x/TileType.TILE_SIZE, (int) y/TileType.TILE_SIZE);
    }

    /**
     * Gets a tile at its coordinates within the map at a specified layer
     * @param layerIndex
     * @param col
     * @param row
     * @return
     */
    public abstract TileType getTileTypeByCoordinates(int layerIndex, int col, int row);
    public abstract void dispose();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();
}
