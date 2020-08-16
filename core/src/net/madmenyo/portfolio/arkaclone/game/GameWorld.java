package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class GameWorld
{
	private Brick[][] bricks;
	private TextureAtlas atlas;

	public GameWorld(TextureAtlas atlas)
	{
		this.atlas = atlas;
	}

	public void update(float delta){

	}

	public Brick[][] getBricks()
	{
		return bricks;
	}

	public void draw(SpriteBatch batch)
	{
		for (int y = 0; y < bricks[0].length; y++){
			for (int x = 0; x < bricks.length; x++){
				bricks[x][y].getSprite().draw(batch);
			}
		}
	}
}
