package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

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

	private Paddle paddle;

	private Vector2 direction = new Vector2(0, 1);
	private float speed = 250;
	private Vector2 velocity = new Vector2();

	private State ballState = State.Serving;

	private Sprite sprite;

	/**
	 * The ball fired by the paddle, initially fixed to the center of the paddle
	 * @param paddle
	 * @param ballRegion
	 */
	public Ball(Paddle paddle, TextureRegion ballRegion)
	{
		this.paddle = paddle;
		sprite = new Sprite(ballRegion);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setOriginBasedPosition(paddle.sprite.getX() + paddle.sprite.getOriginX(), paddle.sprite.getY() + paddle.sprite.getOriginY());

	}

	/**
	 * used to instantiate multiball balls from a ball
	 * @param ball
	 */
	public Ball(Ball ball){
		ballState = State.Moving;
		sprite.set(ball.sprite);
		// Randomizedirection
		direction.rotate(MathUtils.random(360));

	}

	public void update(float delta){

		// Check state, if serving keep moving with paddle, otherwise update normally
		if (ballState.equals(State.Serving)){
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				ballState = State.Moving;
			} else {
				// Should move with paddle
				sprite.setOriginBasedPosition(paddle.sprite.getX() + paddle.sprite.getOriginX(), paddle.sprite.getY() + paddle.sprite.getOriginY());
				return;
			}
		}
		velocity.set(direction).scl(speed * delta);
		sprite.translate(velocity.x, velocity.y);
		// Check for collision on new position value
	}

	public void draw(SpriteBatch batch){
		sprite.draw(batch);
	}
}
