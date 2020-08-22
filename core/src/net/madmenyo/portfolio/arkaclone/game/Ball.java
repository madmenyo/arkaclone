package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class Ball
{
	private enum State{
		Moving,
		Serving
	}

	private State state;

	private Sprite sprite;
	private Rectangle collisionRectangle;

	// Awareness objects
	private PlayField playField;
	private Paddle paddle;
	private List<Brick> bricks;

	public Ball(PlayField playField, Paddle paddle, List<Brick> bricks)
	{
		this.playField = playField;
		this.paddle = paddle;
		this.bricks = bricks;

		state = State.Serving;

	}

	public void update(float delta){

	}
}
