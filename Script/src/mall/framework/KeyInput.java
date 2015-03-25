package mall.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import mall.objects.Bullet;
import mall.window.Handler;

public class KeyInput extends KeyAdapter {// adapter > listener

	Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i); // stores
															// handler.object.get(i)
															// to tempObject

			if (tempObject.getID() == ObjectID.Player) {
				if (key == KeyEvent.VK_D)
					tempObject.setVelX(5);
				if (key == KeyEvent.VK_A)
					tempObject.setVelX(-5);
				if (key == KeyEvent.VK_W && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
				if (key == KeyEvent.VK_SPACE){                          //den tar facing (1 eller -1) sen gånger 5, vilket blir hastigheten                                
					handler.addObject(new Bullet(tempObject.getX(), tempObject.getY()+28, ObjectID.Bullet, tempObject.getFacing()*20 ));
					
				}

			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i); // stores
															// handler.object.get(i)
															// to tempObject

			if (tempObject.getID() == ObjectID.Player) {
				if (key == KeyEvent.VK_D)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_A)
					tempObject.setVelX(0);

			}
		}
	}
}
