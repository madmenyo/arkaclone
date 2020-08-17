package net.madmenyo.portfolio.arkaclone.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


/**
 * ArkaClone [2020]
 * By Menno Gouw
 *
 * Specifies the outer bounds of the playable area and it's background
 */

public class PlayField
{
	private Rectangle rectangle = new Rectangle();
	private TextureRegion sidePiece;
	private TextureRegion cornerPiece;

	public PlayField(TextureRegion sidePiece, TextureRegion cornerPiece)
	{
		this.sidePiece = sidePiece;
		this.cornerPiece = cornerPiece;
		rectangle.setSize(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);
		rectangle.setCenter(0, 0);
	}

	public void draw(SpriteBatch batch){
		//Horizontal border
		int x = (int)rectangle.x;
		int y = (int)(rectangle.y + rectangle.height);
		batch.draw(cornerPiece, x, y);
		batch.draw(cornerPiece, x, y - rectangle.height - 16);
		x += 16;
		for (int i = 0; i < 8; i++){
			batch.draw(sidePiece, x, y);
			// Adds bottom border, should be shown and functional with the specific power up.
			//batch.draw(sidePiece, x, y - rectangle.height - 16);
			x += 48;
		}
		batch.draw(cornerPiece, x, y);
		batch.draw(cornerPiece, x, y - rectangle.height - 16);

		// Vertical border
		x = (int)rectangle.x;
		y = (int)rectangle.y + GameWorld.BORDER_WIDTH;
		for (int i = 0; i < 8; i++){
			batch.draw(sidePiece, x, y, 16, 0, 48, 16, 1, 1, 90);
			batch.draw(sidePiece, x + rectangle.width + GameWorld.BORDER_WIDTH, y, 16, 0, 48, 16, 1, 1, 90);
			y += 48;

		}
	}
}
