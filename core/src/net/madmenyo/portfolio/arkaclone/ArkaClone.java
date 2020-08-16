package net.madmenyo.portfolio.arkaclone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class ArkaClone extends Game
{
	
	@Override
	public void create () {
		Assets assets = new Assets();
		assets.loadAll();
		assets.getAssetManager().finishLoading();

		Stage stage = new Stage(new ExtendViewport(1280, 720));
		setScreen(new MainMenuScreen(stage, assets.getAssetManager()));

	}
}
