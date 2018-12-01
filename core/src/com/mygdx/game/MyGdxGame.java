package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.world.GameMap;
import com.mygdx.game.world.TileType;
import com.mygdx.game.world.TiledGameMap;

public class MyGdxGame extends ApplicationAdapter {
	GameMap gameMap;
	OrthographicCamera camera;
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		// Set up the camera and window size
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
		// This also makes the window and textures stretchable for some reason
		Gdx.graphics.setWindowedMode(800,600);

		gameMap = new TiledGameMap();
	}

	/**
	 * This function is called every 60 seconds, so we must call the map's update
	 * and render methods inside, as well as update the camera
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(camera, batch);
	}
	
	@Override
	public void dispose () {
		gameMap.dispose();
	}
}
