package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.math.Vector2;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class Ball
{
	private Vector2 position;
	private Vector2 currentPosition = new Vector2();
	private Vector2 direction = new Vector2(0, 1);
	private float speed = 0;
	private Vector2 velocity = new Vector2();

	public Ball(Vector2 position)
	{
		this.position = position;
	}

	public void update(float delta){
		currentPosition.set(position);

		velocity.set(direction).scl(speed * delta);
		position.add(velocity);

		// Check for collision on new position value

	}
}
