package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;

import classes.EntityA;
import classes.EntityB;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -7600755214008009002L;

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Space gam";

	private boolean running = false;
	
	private int enemyCount = 5;
	private int enemyKilled = 0;
	
	private Thread thread;
	
	// denna buffrar hela fönstret
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage bg = null;
	
	private boolean isShooting = false;
	
	
	private Player p;
	private Controller c;
	private Textures tex;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;

	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		spriteSheet = loader.loadImage("/spriteSheet.png");
		bg = loader.loadImage("/bg.png");// try and catch?

		
		tex = new Textures(this);
		p = new Player(200, 200, tex);
		c = new Controller(tex,this);
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		this.addKeyListener(new KeyInput(this));
		
		c.createEnemy(enemyCount);
		
	}

	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000.0 / amountOfTicks;
		double delta = 0; // om den ligger efter skyndbar den på.
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
			}
		}
		stop();
	}

	private void tick() {
		p.tick();
		c.tick();
		if(enemyKilled >= enemyCount){
			enemyCount+=2;
			enemyKilled = 0;
			c.createEnemy(enemyCount);
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		// ///////////////////////////////
		g.drawImage(bg, 0, 0, null);
		p.render(g);
		c.render(g);

		// ////////////////////////////////////////
		g.dispose();
		bs.show();

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_D) {
			p.setVelX(5);
		}

		if (key == KeyEvent.VK_A) {
			p.setVelX(-5);
		}

		if (key == KeyEvent.VK_S) {
			p.setVelY(5);
		}

		if (key == KeyEvent.VK_W) {
			p.setVelY(-5);
		}
		if ( key == KeyEvent.VK_SPACE && !isShooting){
			c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
			isShooting = true;
			
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_D) {
			p.setVelX(0);
		}

		if (key == KeyEvent.VK_A) {
			p.setVelX(0);
		}

		if (key == KeyEvent.VK_S) {
			p.setVelY(0);
		}

		if (key == KeyEvent.VK_W) {
			p.setVelY(0);
		}
		if (key == KeyEvent.VK_SPACE)
			isShooting = false;

	}
	

	public static void main(String[] args) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack(); // extends off the window class, not the actual JFrame
						// class. Packs everything...
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void setEnemyKilled(int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}
	

}
