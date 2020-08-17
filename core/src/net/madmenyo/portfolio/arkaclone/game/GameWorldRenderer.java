package net.madmenyo.portfolio.arkaclone.game;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 *
 * This renderer just uses a camera and sets zooming levels based on the amount of pixels in the
 * window. If it has room to zoom to double the pixel size it does.
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

		// ToDo: should be drawing everything here
		batch.begin();
		gameWorld.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		camera.setToOrtho(false, width, height);
		camera.position.set(0, GameWorld.HUD_HEIGHT / 2,0);
		float widthRatio = 1f / (width / (GameWorld.WORLD_WIDTH + GameWorld.BORDER_WIDTH * 2 ));
		float heightRatio = 1f / (height / (GameWorld.WORLD_HEIGHT + GameWorld.BORDER_WIDTH * 2 + GameWorld.HUD_HEIGHT));
		float largest;
		if (widthRatio > heightRatio) largest = widthRatio;
		else largest = heightRatio;
		camera.zoom = largest;
		camera.update();
	}
}
