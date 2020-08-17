package net.madmenyo.portfolio.arkaclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;

import net.madmenyo.portfolio.arkaclone.game.GameWorldRenderer;
import net.madmenyo.portfolio.arkaclone.game.GameWorld;
import net.madmenyo.portfolio.arkaclone.game.IWorldRenderer;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 *
 * The screen that shows the actual play field and the user interface
 */

public class GameScreen extends BaseScreen
{
	private TextureAtlas atlas;

	private GameWorld gameWorld;
	private IWorldRenderer gameWorldRenderer;


	public GameScreen(Stage stage, AssetManager assetManager)
	{
		super(stage, assetManager);
		atlas = assetManager.get(Assets.SHEET);
		gameWorld = new GameWorld(atlas);
		gameWorldRenderer = new GameWorldRenderer(gameWorld);
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
		gameWorldRenderer.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		gameWorldRenderer.resize(width, height);
	}
}
