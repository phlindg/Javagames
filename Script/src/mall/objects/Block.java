package mall.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import mall.framework.GameObject;
import mall.framework.ObjectID;
import mall.framework.Textures;
import mall.window.Game;

public class Block extends GameObject {

	Textures tex = Game.getInstance();
	private int type;

	public Block(float x, float y, int type, ObjectID id) {
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		if(type==0){
			g.drawImage(tex.block[0], (int)x, (int)y, null); //dirt
		}
		if(type==1){
			g.drawImage(tex.block[1], (int)x, (int)y, null); //grass
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
