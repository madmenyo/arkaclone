package net.madmenyo.portfolio.arkaclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.madmenyo.portfolio.arkaclone.game.GameWorld;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class GameScreen extends BaseScreen
{
	/**
	 * Amount of bricks to show horizontally
	 */
	//public static final int WIDTH = 2 * 16;

	private SpriteBatch batch;
	private TextureAtlas atlas;
	private ScreenViewport levelViewport;

	private GameWorld gameWorld;

	public GameScreen(Stage stage, AssetManager assetManager)
	{
		super(stage, assetManager);
		//levelViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		levelViewport = new ScreenViewport();
		levelViewport.setUnitsPerPixel(.5f);

		batch = new SpriteBatch();
		atlas = assetManager.get(Assets.SHEET);
		gameWorld = new GameWorld(atlas);
	}

	@Override
	public void show()
	{
		super.show();
	}

	@Override
	public void update(float delta)
	{

		gameWorld.update(delta);

	}

	@Override
	public void draw()
	{
		batch.setProjectionMatrix(levelViewport.getCamera().combined);
		batch.begin();
		gameWorld.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		System.out.println("Window width: " + width);
		System.out.println("World width " + GameWorld.WORLD_WIDTH);
		if (width < GameWorld.WORLD_WIDTH * 2){
			levelViewport.setUnitsPerPixel(1f);
			Gdx.app.log("GameScreen", "UPP set to 1f");
		} else if (width < GameWorld.WORLD_WIDTH * 4){
			levelViewport.setUnitsPerPixel(.5f);
			Gdx.app.log("GameScreen", "UPP set to .5f");
		} else {
			levelViewport.setUnitsPerPixel(.25f);
			Gdx.app.log("GameScreen", "UPP set to .25f");
		}
		levelViewport.update(width, height);
	}
}
