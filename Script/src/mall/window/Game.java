package mall.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import mall.framework.KeyInput;
import mall.framework.ObjectID;
import mall.framework.Textures;
import mall.objects.Block;
import mall.objects.Flag;
import mall.objects.Player;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static boolean running = false;
	private Thread thread;
	public static int WIDTH, HEIGHT;

	public BufferedImage level = null, clouds = null;

	// Object
	Handler handler;
	Camera cam;
	static Textures tex;

	Random rand = new Random();

	public static int LEVEL = 1;

	private void init() {// den initialiserar allt d� den �r called innan allt
							// annat (se method run)
		WIDTH = getWidth();
		HEIGHT = getHeight();

		tex = new Textures();

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/mario.png"); // loading the level
		clouds = loader.loadImage("/Clouds 2.png"); // loading background
		cam = new Camera(0, 0); // startcoords f�r kameran
		handler = new Handler(cam);
		

		handler.loadImageLevel(level);

		// handler.addObject(new Player(100, 100, handler,ObjectID.Player));

		// handler.createLevel();

		this.addKeyListener(new KeyInput(handler));
	}

	public synchronized void start() { // synchronized �r f�r threads
		if (running)
			return; // detta �r bara failsafe

		running = true;
		thread = new Thread(this); // bara this beh�vs, appearently
		thread.start();
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			} // denna loop updaterar 60 ggr per sekund men renderar s� fort de
				// g�r
		}

	}

	private void tick() {
		handler.tick(); // ist�llet f�r att ticka alla s� tickar handler.tick
						// alla �t dig.
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID() == ObjectID.Player) {
				cam.tick(handler.object.get(i));
				// den loopar runt o n�r den hittar playern s� tickar den(method
				// i camera).
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy(); // this �r till
														// Canvas!!!!!!
		if (bs == null) {
			this.createBufferStrategy(3); // en bs �r att man laddar bilder
											// bakom,
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		// allt dras mellan detta och dispose

		g.setColor(new Color(25, 191, 224));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(clouds, 0, 0, this);

		g2d.translate(cam.getX(), cam.getY()); // begin of cam

		// allting mellan dessa tv� translate saker p�verkas av kameran

		handler.render(g);

		g2d.translate(-cam.getX(), -cam.getY()); // end of cam

		g.dispose();
		bs.show();

	}


	public static Textures getInstance() {
		return tex;
	}

	public static void main(String[] args) {
		new Window(1080, 600, "Neon Platformer", new Game());
	}

}
