package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * ArkaClone [2020]
 * By Menno Gouw
 */

public class Paddle
{
	Sprite sprite;
	private float speed = 250;

	public Paddle(TextureRegion region)
	{
		sprite = new Sprite(region);
		// Set origin at top center
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight());
		sprite.setOriginBasedPosition(GameWorld.WORLD_WIDTH / 2, -GameWorld.WORLD_HEIGHT / 2 + 32);
	}

	public void update(float delta){
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			sprite.translateX(-speed * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			sprite.translateX(speed * delta);
		}

	}

	public void draw(SpriteBatch batch){
		sprite.draw(batch);
	}
}
