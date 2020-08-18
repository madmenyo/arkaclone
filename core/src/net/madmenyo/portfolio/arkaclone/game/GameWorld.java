package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.List;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 *
 * The GameWorld class is a wrapper class for everything in the game and gives access to dimensions.
 */

public class GameWorld
{
	public static final int BRICK_WIDTH = 32;
	public static final int BRICK_HEIGHT = 16;

	public static final int BORDER_WIDTH = 16;

	public static final int HORIZONTAL_BRICKS = 12;
	public static final int VERTICAL_BRICKS = 16;

	public static final int PADDLE_SPACE = 128;

	public static final float WORLD_WIDTH = BRICK_WIDTH * HORIZONTAL_BRICKS;
	public static final float WORLD_HEIGHT = (BRICK_HEIGHT * VERTICAL_BRICKS) + PADDLE_SPACE;

	public static final float HUD_HEIGHT = 32;


	private TextureAtlas atlas;

	private PlayField playField;

	private Paddle paddle;
	private List<Brick> bricks;
	private Ball ball;

	public GameWorld(TextureAtlas atlas)
	{
		this.atlas = atlas;

		playField = new PlayField(atlas.findRegion("borderside_yellow"), atlas.findRegion("bordercorner_yellow"));
		paddle = new Paddle(atlas.findRegion("paddle"));
		createTestBricks(12, 8);
		createTestBall();
	}

	/**
	 * Just test method for now
	 */
	private void createTestBall()
	{
		ball = new Ball(paddle, bricks, playField, atlas.findRegion("ball_big_darkblue"));
	}

	/**
	 * Just test method for now
	 * @param width amount of bricks horizontal
	 * @param height amount of bricks vertical
	 */
	private void createTestBricks(int width, int height)
	{
		bricks = new ArrayList<>();
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				bricks.add(new Brick(x, y, atlas.findRegion("brick_blue")));
			}
		}
	}

	public void update(float delta){
		paddle.update(delta);
		ball.update(delta);
	}

	public void draw(SpriteBatch batch)
	{
		playField.draw(batch);
		paddle.draw(batch);
		for (Brick brick : bricks){
			brick.getSprite().draw(batch);
		}
		ball.draw(batch);
	}
}
