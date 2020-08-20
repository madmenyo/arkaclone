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

	private Paddle paddle;
	private List<Brick> bricks;
	private PlayField playField;

	private Vector2 direction = new Vector2(0, 1);
	private float speed = 250;
	private Vector2 velocity = new Vector2();

	private State ballState = State.Serving;

	private Sprite sprite;
	private Circle collisionCircle = new Circle();

	private Vector2 currentPosition = new Vector2();
	private Vector2 endPosition = new Vector2();

	/**
	 * The ball fired by the paddle, initially fixed to the center of the paddle
	 * @param paddle
	 * @param bricks
	 * @param playField
	 * @param ballRegion
	 */
	public Ball(Paddle paddle, List<Brick> bricks, PlayField playField, TextureRegion ballRegion)
	{
		this.paddle = paddle;
		this.bricks = bricks;
		this.playField = playField;
		currentPosition.set(paddle.sprite.getX() + paddle.sprite.getOriginX(), paddle.sprite.getY() + paddle.sprite.getOriginY());
		endPosition.set(currentPosition);
		// Set sprite based on current position;
		sprite = new Sprite(ballRegion);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setOriginBasedPosition(currentPosition.x, currentPosition.y);
	}

	/**
	 * perhaps used to instantiate multiball balls from a ball
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
				endPosition.set(paddle.sprite.getX() + paddle.sprite.getOriginX(), paddle.sprite.getY() + paddle.sprite.getOriginY());
				positionSprite();
				return;
			}
		}
		velocity.set(direction).scl(speed * delta);
		//Set the collision circle at end of frame position for detection;
		endPosition.set(currentPosition).add(velocity);
		//handleCollision(delta);
		//sprite.translate(velocity.x, velocity.y);
		// ToDo: Check for collision on new position value

		positionSprite();
	}

	private void positionSprite(){
		currentPosition.set(endPosition);
		sprite.setOriginBasedPosition(currentPosition.x, currentPosition.y);
	}

	public void draw(SpriteBatch batch){
		sprite.draw(batch);
	}


	/**
	 * Handles all collision for the ball
	 */
	public void handleCollision(float delta){
		collisionCircle.set(sprite.getX() + sprite.getOriginX() + velocity.x, sprite.getY() + sprite.getOriginY() + velocity.y, sprite.getWidth() / 2);
		// Add some statement to see what part the ball is and then check vs possible candidates
		if (paddleCollision()) return;
		if (fieldCollision()) return;
		if (brickCollision(delta)){
			ballState = State.Serving;
			return;
		}
	}

	/**
	 * Handles collision for the bricks
	 * @param delta
	 * @return returns true if there is a brick collision
	 */
	private boolean brickCollision(float delta)
	{
		// Loop trough all bricks
		for (Brick brick : bricks){
			// Check cheap rectangles
			if (sprite.getBoundingRectangle().overlaps(brick.getSprite().getBoundingRectangle())){
				// Set bounding circle and check if there really is a collision, if not continue iterating
				collisionCircle.set(sprite.getX() + sprite.getOriginX(), sprite.getY() + sprite.getOriginY(), sprite.getWidth() / 2);
				if (!Intersector.overlaps(collisionCircle, brick.getSprite().getBoundingRectangle())) continue;
				//Create rectangle to generate a overlap rect
				Vector2 intersection = handleRectangleCollision(brick.getSprite().getBoundingRectangle(), delta);
				return true;
			}
		}
			return false;
	}


	/**
	 * Handles collision for rectangles, rectangles are slightly harder to predict vs a circle and the ball needs to change direction based on the colliding side.
	 * @param boundingRectangle
	 * @param delta
	 * @return The final ball position after collision
	 */
	private Vector2 handleRectangleCollision(Rectangle boundingRectangle, float delta)
	{
		// Get start point and end point
		Vector2 startPoint = new Vector2();
		sprite.getBoundingRectangle().getCenter(startPoint);
		Vector2 endPoint = startPoint.cpy().add(velocity);

		// Try intersecting it with the 4 sides of rectangle to find the side it collided with;
		// Get 4 verts
		Vector2 v1 = new Vector2(boundingRectangle.x, boundingRectangle.y);
		Vector2 v2 = new Vector2(boundingRectangle.x, boundingRectangle.y + boundingRectangle.height);
		Vector2 v3 = new Vector2(boundingRectangle.x + boundingRectangle.width, boundingRectangle.y + boundingRectangle.height);
		Vector2 v4 = new Vector2(boundingRectangle.x + boundingRectangle.width, boundingRectangle.y);

		Vector2 intersectionOut = new Vector2();
		if (Intersector.intersectSegments(startPoint, endPoint, v1, v2, intersectionOut)){
			Gdx.app.log("Ball", "intersection at left side: " + intersectionOut);
			// Get distance to edge of rectangle
			float ditanceToEdge = currentPosition.dst(intersectionOut) - sprite.getWidth() / 2;

			// Get the distance left and intersection point
			float distanceLeft = remainingDistance(intersectionOut, speed * delta);
			// Set currentPosition and Endposition so the ball hugs the edge using intersectionOut

			//correctly rotate direction based on side the ball hit and

			// now rerun with the distance that is left and the new direction

			return intersectionOut;
		} else if (Intersector.intersectSegments(startPoint, endPoint, v2, v3, intersectionOut))
		{
			Gdx.app.log("Ball", "intersection at top side: " + intersectionOut);
			return intersectionOut;
		} else if (Intersector.intersectSegments(startPoint, endPoint, v3, v4, intersectionOut))
		{
			Gdx.app.log("Ball", "intersection at right side: " + intersectionOut);
			return intersectionOut;
		} else if (Intersector.intersectSegments(startPoint, endPoint, v4, v1, intersectionOut))
		{
			Gdx.app.log("Ball", "intersection at bottom side: " + intersectionOut);
			return intersectionOut;
		}

		Gdx.app.log("Ball", "No intersection? This method should not have triggered then");
		return null;
	}

	private boolean fieldCollision()
	{
		return false;
	}

	private boolean paddleCollision()
	{
		return false;
	}

	private float remainingDistance(Vector2 collisionPoint, float totalDistance){
		return totalDistance - currentPosition.dst(collisionPoint);
	}
}
