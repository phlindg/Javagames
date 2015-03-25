package mall.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import mall.framework.GameObject;
import mall.framework.ObjectID;
import mall.framework.Textures;
import mall.window.Animation;
import mall.window.Camera;
import mall.window.Game;
import mall.window.Handler;

public class Player extends GameObject { // denna class har allt med playern att
											// göra, obviously
	private float width = 48, height = 96;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	// 1 = right
	// -1 = left

	private Handler handler;
	private Camera cam;

	Textures tex = Game.getInstance();

	private Animation playerWalk, playerWalkLeft;

	public Player(float x, float y, Handler handler, Camera cam,ObjectID id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
		// man hoppar över idle då den inte där idle när man går
		playerWalk = new Animation(5, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5], tex.player[6]);
		playerWalkLeft = new Animation(5, tex.player[8], tex.player[9], tex.player[10], tex.player[11], tex.player[12], tex.player[13]);
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (velX < 0)
			facing = -1;
		else if (velX > 0)
			facing = 1;

		if (falling || jumping) {
			velY += gravity;

			// denna är för att kontrollera att de inte går för fort.
			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		Collision(object);

		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ObjectID.Block) { // om denna
														// class(player) nuddar
														// Block, gör något
				if (getBounds().intersects(tempObject.getBounds())) // bara
																	// bottom
																	// half (se
																	// metheods
																	// nedan)
				{
					y = tempObject.getY() - height + 1; // så att den landar
														// perfekt
					velY = 0;
					falling = false;
					jumping = false;
				} else
					falling = true;

				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 32;
				}
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
				}
			} else if (tempObject.getID() == ObjectID.Flag) {
				// switch levels
				if (getBounds().intersects(tempObject.getBounds())){
					handler.switchLevel();
				}
			}
		}
	}

	public void render(Graphics g) {

		g.setColor(Color.blue);

		if (jumping) {
			if (facing == 1) {
				g.drawImage(tex.playerJump[2], (int) x, (int) y, 48, 96, null);
			} else if (facing == -1) {
				g.drawImage(tex.playerJump[3], (int) x, (int) y, 48, 96, null);
			}
		} else {
			if (velX != 0) {
				if (facing == 1)
					playerWalk.drawAnimation(g, (int) x, (int) y, 48, 96);
				else
					playerWalkLeft.drawAnimation(g, (int) x, (int) y, 48, 96);
			} else {// om man inte går står den stilla, duh
				if (facing == 1)
					g.drawImage(tex.player[0], (int) x, (int) y, 48, 96, null);
				else if (facing == -1)
					g.drawImage(tex.player[7], (int) x, (int) y, 48, 96, null);
			}

		}

		// denna är för att rita gubben i
		g.setColor(Color.red);
		/*
		 * Graphics2D g2d = (Graphics2D) g; // detta är så att man kan rita i 2d
		 * 
		 * g2d.draw(getBounds()); g2d.draw(getBoundsRight());
		 * g2d.draw(getBoundsLeft()); g2d.draw(getBoundsTop());
		 */
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) ((int) y + (height / 2)), (int) width / 2, (int) height / 2);
		// denna rectangle är för att se collision
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2, (int) height / 2);
		// denna rectangle är för att se collision
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + (width - 5)), (int) y + 5, (int) 5, (int) height - 10);
		// denna rectangle är för att se collision
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
		// denna rectangle är för att se collision
	} // DETTA MÅSTE JAG KOLLA UPP VARFÖR MAN GÖR SÅ

}
