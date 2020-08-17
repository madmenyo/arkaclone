package net.madmenyo.portfolio.arkaclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 *
 * A base screen, stage will be reused and cleared each screen.
 */

public abstract class BaseScreen extends ScreenAdapter
{
	protected Stage stage;
	protected AssetManager assetManager;
	protected Skin skin;
	protected Table mainTable;

	public BaseScreen(Stage stage, AssetManager assetManager)
	{
		this.stage = stage;
		this.assetManager = assetManager;
		skin = assetManager.get(Assets.SKIN);
	}

	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
		stage.clear();
		mainTable = new Table();
		mainTable.setFillParent(true);
		stage.addActor(mainTable);
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		update(delta);
		draw();
		stage.draw();
	}

	/**
	 * All logic should go in this method
	 * @param delta time since last frame
	 */
	public abstract void update(float delta);

	/**
	 * All drawing should go in this method
	 */
	public abstract void draw();

	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
	}

	@Override
	public void dispose()
	{
		assetManager.dispose();
		stage.dispose();
	}
}
