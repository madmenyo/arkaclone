package net.madmenyo.portfolio.arkaclone.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import net.madmenyo.portfolio.arkaclone.ArkaClone;

public class DesktopLauncher {
	public static void main (String[] arg) {
		PackImages();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280 / 2;
		config.height = 720 / 2;
		config.forceExit = false;
		new LwjglApplication(new ArkaClone(), config);
	}

	private static void PackImages()
	{
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.fast = true;

		String input = "../../images";
		String output = "images";
		String name = "images.atlas";

		TexturePacker.process(settings, input, output, name);

	}
}
