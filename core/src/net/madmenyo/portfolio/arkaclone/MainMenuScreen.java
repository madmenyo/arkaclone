package net.madmenyo.portfolio.arkaclone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class MainMenuScreen extends BaseScreen
{

	public MainMenuScreen(Stage stage, AssetManager assetManager)
	{
		super(stage, assetManager);
	}

	@Override
	public void show()
	{
		super.show();

		addButton("Play");
		addButton("Options").addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				((Game)Gdx.app.getApplicationListener()).setScreen(new OptionScreen(stage, assetManager));
			}
		});
		addButton("Quit").addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				Gdx.app.exit();
			}
		});
	}

	private TextButton addButton(String name){
		TextButton button = new TextButton(name, skin);
		mainTable.add(button).width(600).height(60).padBottom(40);
		mainTable.row();
		return button;
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
