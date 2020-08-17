package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Rectangle;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class Brick
{
	private Sprite sprite;

	public Brick(int x, int y, TextureRegion region)
	{
		float offset = GameWorld.WORLD_WIDTH / 2;
		sprite = new Sprite(region);
		sprite.setBounds(x * GameWorld.BRICK_WIDTH - offset, y * GameWorld.BRICK_HEIGHT, GameWorld.BRICK_WIDTH, GameWorld.BRICK_HEIGHT);
	}

	public Sprite getSprite()
	{
		return sprite;
	}
}
