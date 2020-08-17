package net.madmenyo.portfolio.arkaclone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Initial class called by the specific platform launcher, loads assets and sets the screen.
 */
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
