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
	private float speed = 150;
	private Vector2 velocity = new Vector2();

	private State ballState = State.Serving;

	private Sprite sprite;
	private Circle collisionCircle = new Circle();
	private Rectangle collisionRectangle = new Rectangle();

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

		// calculate where ball will be moved
		calculateEndPosition(speed * delta);
		// detect collision
		handleCollision(delta);
		positionSprite();
	}

	/**
	 * Calculates and sets endPosition based on currentPosition, direction and speed
	 * @param distance the distance, either calculated after a collision or by multiplieing delta by speed
	 */
	private void calculateEndPosition(float distance) {
		velocity.set(direction).scl(distance);
		endPosition.set(currentPosition).add(velocity);
	}

	private void positionSprite(){
		currentPosition.set(endPosition);
		sprite.setOriginBasedPosition(currentPosition.x, currentPosition.y);
	}

	public void draw(SpriteBatch batch){
		sprite.draw(batch);
	}


	/**
	 * Handles all collision for the ball, first calculate end position then run this.
	 * If there is a collision set current position at the edge of the collision, and run this again with newly calculated end position
	 */
	public void handleCollision(float delta){
		calculateCollisionShapes();

		// Add some statement to see what part the ball is and then check vs possible candidates
		if (paddleCollision()) return;
		if (fieldCollision()) return;
		if (brickCollision(delta)){
			//ballState = State.Serving;
			return;
		}
	}

	private void calculateCollisionShapes()
	{
		collisionCircle.set(endPosition.x, endPosition.y, sprite.getWidth() / 2);
		collisionRectangle.set(endPosition.x, endPosition.y, sprite.getWidth(), sprite.getHeight());
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
			if (collisionRectangle.overlaps(brick.getSprite().getBoundingRectangle())){
				// Check if there really is a collision on circle, if not continue iterating
				if (!Intersector.overlaps(collisionCircle, brick.getSprite().getBoundingRectangle())) continue;
				//Create rectangle to generate a overlap rect
				float distanceLeft = handleRectangleCollision(brick.getSprite().getBoundingRectangle(), delta);
				calculateEndPosition(distanceLeft);
				// Calculate collision again
				handleCollision(delta);
				return true;
			}
		}
			return false;
	}


	/**
	 * Handles collision for rectangles, sets the ball at the edge of the collision and returns the remaining distance.
	 * @param boundingRectangle
	 * @param distanceToTravel
	 * @return The remaining distance. It's the speed * delta minus distance to edge of collision
	 */
	private float handleRectangleCollision(Rectangle boundingRectangle, float distanceToTravel)
	{
		// Get start point and end point
		Vector2 startPoint = new Vector2();
		startPoint.set(currentPosition);
		//sprite.getBoundingRectangle().getCenter(startPoint);
		Vector2 endPoint = new Vector2();
		//endPoint.set(direction).scl(distanceToTravel).add(startPoint);
		//Vector2 endPoint = startPoint.cpy().add(velocity);
		endPoint.set(direction).scl(collisionCircle.radius).add(endPosition);

		System.out.println("Radius: " + sprite.getWidth());
		System.out.println("Circle: " + collisionCircle);
		System.out.println("Curpos: " + currentPosition);
		System.out.println("EndPos: " + endPosition);
		System.out.println("Startpoint: " + startPoint);
		System.out.println("Endpoint: " + endPoint);


		// Try intersecting it with the 4 sides of rectangle to find the side it collided with;
		// Get 4 verts
		Vector2 v1 = new Vector2(boundingRectangle.x, boundingRectangle.y);
		Vector2 v2 = new Vector2(boundingRectangle.x, boundingRectangle.y + boundingRectangle.height);
		Vector2 v3 = new Vector2(boundingRectangle.x + boundingRectangle.width, boundingRectangle.y + boundingRectangle.height);
		Vector2 v4 = new Vector2(boundingRectangle.x + boundingRectangle.width, boundingRectangle.y);

		//System.out.println(startPoint + " / " + endPoint);
		//System.out.println(v1 + " / " + v4);

		Vector2 intersectionOut = new Vector2();
		// left side
		if (Intersector.intersectSegments(startPoint, endPoint, v1, v2, intersectionOut)){
			Gdx.app.log("Ball", "intersection at left side: " + intersectionOut);
			return distanceToTravel;
		}
		// Top side
		else if (Intersector.intersectSegments(startPoint, endPoint, v2, v3, intersectionOut))
		{
			Gdx.app.log("Ball", "intersection at top side: " + intersectionOut);
			return distanceToTravel;
		}
		// right side
		else if (Intersector.intersectSegments(startPoint, endPoint, v3, v4, intersectionOut))
		{
			Gdx.app.log("Ball", "intersection at right side: " + intersectionOut);
			return distanceToTravel;
		}
		// Bottom side
		else if (Intersector.intersectSegments(startPoint, endPoint, v4, v1, intersectionOut))
		{
			Gdx.app.log("Ball", "intersection at bottom side: " + intersectionOut);
			// Get distance to edge of rectangle
			float distanceToEdge = currentPosition.dst(intersectionOut) - sprite.getWidth() / 2;

			// Calculate remaining distance
			float distanceLeft = remainingDistance(intersectionOut, distanceToEdge);
			// Set currentPosition and Endposition so the ball hugs the edge using intersectionOut
			System.out.println(distanceLeft);

			// find colliding position
			Vector2 colPos = new Vector2();
			colPos.set(direction).scl(distanceToEdge).add(currentPosition);

			currentPosition.set(colPos);
			endPosition.set(currentPosition);

			//correctly rotate direction based on side the ball hit and
			direction.y = -direction.y;

			// now rerun with the distance that is left and the new direction
			return distanceLeft;
		}

		Gdx.app.log("Ball", "No intersection? This method should not have triggered then");
		return 0;
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
