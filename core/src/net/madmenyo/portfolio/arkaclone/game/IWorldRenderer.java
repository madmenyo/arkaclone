package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 *
 * Helper interface, eventually probably just one render method works out but I needed testing on
 * different types of rendering in order to preserve the pixel density of pixel art.
 */

public interface IWorldRenderer
{
	void draw();
	void resize(int width, int height);
}
