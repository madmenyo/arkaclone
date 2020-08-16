package net.madmenyo.portfolio.arkaclone;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

class OptionScreen extends BaseScreen
{

	public OptionScreen(Stage stage, AssetManager assetManager)
	{
		super(stage, assetManager);
	}

	@Override
	public void show()
	{
		super.show();
		mainTable.top();
		mainTable.pad(20);
		mainTable.add(new Label("Options Screen",skin)).center().padBottom(20).colspan(2);
		mainTable.row();

		mainTable.add(new Label("Master Volume", skin)).width(300);
		Slider masterSlider = new Slider(0, 100, 1, false, skin);
		mainTable.add(masterSlider).width(300);
		mainTable.row();

		mainTable.add(new Label("Music Volume", skin)).width(300);
		Slider musicSlider = new Slider(0, 100, 1, false, skin);
		mainTable.add(musicSlider).width(300);
		mainTable.row();

		mainTable.add(new Label("Sound Volume", skin)).width(300);
		Slider soundSlider = new Slider(0, 100, 1, false, skin);
		mainTable.add(soundSlider).width(300);
		mainTable.row();
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
