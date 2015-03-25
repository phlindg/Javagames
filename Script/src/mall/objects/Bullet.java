package mall.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import mall.framework.GameObject;
import mall.framework.ObjectID;

public class Bullet extends GameObject{

	public Bullet(float x, float y, ObjectID id, int velX) {
		super(x, y, id);
		this.velX = velX;
	}

	public void tick(LinkedList<GameObject> object) {
		x+=velX;
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x,(int)y,8,8);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, 8, 8);
	}

}
