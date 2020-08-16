package net.madmenyo.portfolio.arkaclone;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class Assets
{
	private AssetManager assetManager = new AssetManager();

	public static final AssetDescriptor<TextureAtlas> SHEET = new AssetDescriptor<TextureAtlas>("images/images.atlas", TextureAtlas.class);
	public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<Skin>("gui/Holo-dark-hdpi.json", Skin.class, new SkinLoader.SkinParameter("gui/Holo-dark-hdpi.atlas"));

	/**
	 * Loads all assets, if ever needs to dispose individual assets create additional loaders for these.
 	 */
	public void loadAll(){
		assetManager.load(SKIN);
	}

	public AssetManager getAssetManager()
	{
		return assetManager;
	}
}
