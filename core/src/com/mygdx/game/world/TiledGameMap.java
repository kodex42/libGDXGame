package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap extends GameMap {

    int frameCount=0;
    int grassState=1;
    int lastGrassState=0;

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;
    TextureRegion grassTextureRegion;

    public TiledGameMap() {
        tiledMap = new TmxMapLoader().load("core/assets/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        grassTextureRegion = ((TiledMapTileLayer)tiledMap.getLayers().get(0)).getCell(7,7).getTile().getTextureRegion();
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch batch) {
        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.render(camera, batch);
        batch.end();
    }

    @Override
    public void update(float delta) {
        frameCount++;
        super.update(delta);
        updateGrass();
    }

    private void updateGrass() {
        if (frameCount%45 == 0) {
            switch (grassState) {
                case 0:
                    lastGrassState = grassState;
                    grassTextureRegion.setRegion(128,32,32,32);
                    grassState++;
                    break;
                case 1:
                    if (lastGrassState == 0) {
                        lastGrassState = grassState;
                        grassState++;
                    }
                    else {
                        lastGrassState = grassState;
                        grassState--;
                    }
                    grassTextureRegion.setRegion(0,64,32,32);
                    break;
                case 2:
                    lastGrassState = grassState;
                    grassTextureRegion.setRegion(32,64,32,32);
                    grassState--;
                    break;
            }
        }
    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    @Override
    public int getWidth() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
    }

    @Override
    public int getLayers() {
        return tiledMap.getLayers().getCount();
    }

    @Override
    public TileType getTileTypeByCoordinates(int layerIndex, int col, int row) {
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(layerIndex);
        TiledMapTileLayer.Cell cell = layer.getCell(col, row);

        if (cell != null) {
            TiledMapTile tile = cell.getTile();

            if (tile != null) {
                int id = tile.getId();
                return TileType.getTileTypeById(id);
            }
         }
        return null;
    }
}
