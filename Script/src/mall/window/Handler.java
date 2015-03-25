package mall.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import mall.framework.GameObject;
import mall.framework.ObjectID;
import mall.objects.Block;
import mall.objects.Flag;
import mall.objects.Player;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;
	private Camera cam;
	
	private BufferedImage level2 = null;
	
	public Handler(Camera cam){
		this.cam = cam;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level2 = loader.loadImage("/mario2.png"); // loading the level
	}

	// efter detta ska man rendera och updatea listan.

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);

			tempObject.tick(object);
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);

			tempObject.render(g);
		}
	}
	

	public void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && green == 255 && blue == 255) {
					addObject(new Block(xx * 32, yy * 32, 0, ObjectID.Block));
				}
				if (red == 128 && green == 128 && blue == 128) {
					addObject(new Block(xx * 32, yy * 32, 1, ObjectID.Block));
				}
				if (red == 0 && green == 0 && blue == 255) {
					addObject(new Player(xx * 32, yy * 32, this, cam, ObjectID.Player));
				}
				if (red == 255 && green == 0 && blue == 0) {
					addObject(new Flag(xx * 32, yy * 32, ObjectID.Flag));
				}

			}
		}

	}
	public void switchLevel(){
		clearLevel();
		cam.setX(0);
		
		switch(Game.LEVEL){
		case 1:
			loadImageLevel(level2);
			break;
		}
		Game.LEVEL++;
	}
	private void clearLevel(){
		object.clear();
	}
	public void addObject(GameObject object){
		this.object.add(object); //this reffers to the list.
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	/*public void createLevel(){
		//Vänster sida!
		for (int yy = 0; yy < Game.HEIGHT+32; yy+=32){
			addObject(new Block(0, yy, ObjectID.Block));
		}
		//GOLVET
		for (int xx = 0; xx < Game.WIDTH*2; xx+=32){
			addObject(new Block(xx, Game.HEIGHT-32, ObjectID.Block));
		}
		//Mittensaken!
		for (int xx = 200; xx < 600; xx+=32){
			addObject(new Block(xx, 400, ObjectID.Block));
		}
		//Höger sida!
	for (int yy = 0; yy < Game.HEIGHT+32; yy+=32){
			addObject(new Block(Game.WIDTH-32, yy, ObjectID.Block));
		}
	}*/

}
