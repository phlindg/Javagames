package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import libs.Animation;
import classes.EntityB;


public class Enemy extends GameObject implements EntityB {

	Random rand = new Random();

	private Textures tex;
	Animation anim;
	private Game game;
	private Controller c; //pga att vi har remove där.

	private int speed = rand.nextInt(3) + 1;

	public Enemy(double x, double y, Textures tex, Controller c, Game game) {
		super(x, y);
		this.tex = tex;
		this.c=c;
		this.game=game;
		anim = new Animation(5,tex.enemy[0],tex.enemy[1],tex.enemy[2]);
	}

	public void tick() { // om man vill flytta på saken måste den ha update
							// eller tick
		y += speed;
		if (y > (Game.HEIGHT * Game.SCALE)) {
			speed = rand.nextInt(3) + 1;
			y = -10;
			x = rand.nextInt(Game.WIDTH * Game.SCALE);
		}
		if(Physics.Collision(this, game.ea)){
			c.removeEntity(this);
			game.setEnemyKilled(game.getEnemyKilled() +1);
		}
		anim.runAnimation();

	}

	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

}
