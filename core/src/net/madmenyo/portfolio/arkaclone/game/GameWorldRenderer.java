package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class GameWorldRenderer implements IWorldRenderer
{
	private GameWorld gameWorld;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	public GameWorldRenderer(GameWorld gameWorld)
	{
		this.gameWorld = gameWorld;
		camera = new OrthographicCamera();
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.position.set(0, 0, 0);
		camera.zoom = .5f;
	}

	@Override
	public void draw()
	{
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		gameWorld.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		camera.setToOrtho(false, width, height);
		camera.position.set(0, 0,0);
		camera.zoom = 1f / (int)(width / GameWorld.WORLD_WIDTH);
		camera.update();
	}
}
