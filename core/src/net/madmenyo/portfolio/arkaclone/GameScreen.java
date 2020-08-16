package net.madmenyo.portfolio.arkaclone;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;

import net.madmenyo.portfolio.arkaclone.game.Brick;
import net.madmenyo.portfolio.arkaclone.game.Map;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class GameScreen extends BaseScreen
{


	private SpriteBatch batch;
	private TextureAtlas atlas;

	private Map map;

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

		batch.begin();

		batch.end();

	}

	@Override
	public void draw()
	{

	}
}
