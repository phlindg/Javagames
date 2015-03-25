package mall.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject { // jag tror att en abstract class är nån som
									// man kan extenda o ta methods ifrån.
	// kan va jävligt bra att ha

	protected float x, y;
	protected ObjectID id;
	protected float velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected int facing = 1;

	public GameObject(float x, float y, ObjectID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick(LinkedList<GameObject> object); // detta är för
																// collision
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public boolean isFalling() {
		return falling;
	}
	public boolean isJumping() {
		return jumping;
	}
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	public int getFacing(){
		return facing;
	}

	public ObjectID getID() {
		return id;
	} // detta kollar vilken sak man vill ha, t.ex. player

}
