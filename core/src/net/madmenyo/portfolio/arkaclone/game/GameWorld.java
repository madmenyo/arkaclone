package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class GameWorld
{
	public static final int BRICK_WIDTH = 32;
	public static final int BRICK_HEIGHT = 16;

	public static final int BORDER_WIDTH = 16;

	public static final int HORIZONTAL_BRICKS = 12;

	public static final float WORLD_WIDTH = BRICK_WIDTH * 12 + BORDER_WIDTH * 2;


	private Brick[][] bricks;
	private TextureAtlas atlas;

	public GameWorld(TextureAtlas atlas)
	{
		this.atlas = atlas;

		createTestBricks(12, 8);
	}

	private void createTestBricks(int width, int height)
	{
		bricks = new Brick[width][height];

		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				bricks[x][y] = new Brick(x, y, atlas.findRegion("brick_blue"));
			}
		}
	}

	public void update(float delta){

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
