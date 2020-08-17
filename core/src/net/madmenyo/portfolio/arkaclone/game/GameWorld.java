package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

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
	public static final int VERTICAL_BRICKS = 16;

	public static final int PADDLE_SPACE = 128;

	public static final float WORLD_WIDTH = BRICK_WIDTH * HORIZONTAL_BRICKS + BORDER_WIDTH * 2;
	public static final float WORLD_HEIGHT = (BRICK_HEIGHT * VERTICAL_BRICKS) + (BORDER_WIDTH * 2) + PADDLE_SPACE;


	private TextureAtlas atlas;

	private Brick[][] bricks;
	private Ball ball;

	public GameWorld(TextureAtlas atlas)
	{
		this.atlas = atlas;

		createTestBricks(12, 8);
		createTestBall();
	}

	private void createTestBall()
	{
		ball = new Ball(new Vector2(0, 20), atlas.findRegion("ball_blue"));
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
		ball.update(delta);
	}

	public void draw(SpriteBatch batch)
	{
		for (int y = 0; y < bricks[0].length; y++){
			for (int x = 0; x < bricks.length; x++){
				bricks[x][y].getSprite().draw(batch);
			}
		}

		ball.draw(batch);
	}
}
