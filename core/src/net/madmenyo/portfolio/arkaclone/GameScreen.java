package net.madmenyo.portfolio.arkaclone;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;

import net.madmenyo.portfolio.arkaclone.game.Brick;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class GameScreen extends BaseScreen
{


	private SpriteBatch batch;
	private TextureAtlas atlas;

	private Brick[][] bricks;

	public GameScreen(Stage stage, AssetManager assetManager)
	{
		super(stage, assetManager);
		batch = new SpriteBatch();
	}

	@Override
	public void show()
	{
		super.show();
	}

	@Override
	public void update(float delta)
	{

	}

	@Override
	public void draw()
	{

	}
}
