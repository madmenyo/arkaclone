package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.math.Vector2;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class Ball
{
	private Vector2 position;
	private Vector2 direction = new Vector2(0, 1);
	private float speed = 0;

	public Ball(Vector2 position)
	{
		this.position = position;
	}
}
