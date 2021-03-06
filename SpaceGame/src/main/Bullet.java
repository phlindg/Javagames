package main;

import java.awt.Graphics;
import java.awt.Rectangle;

import libs.Animation;
import classes.EntityA;

public class Bullet extends GameObject implements EntityA{
	
	
	private Textures tex;
	private Game game;
	
	Animation anim;
	
	public Bullet(double x, double y, Textures tex, Game game){
		super(x,y);
		this.tex= tex;
		this.game = game;
		
		anim = new Animation(5,tex.missile[0],tex.missile[1],tex.missile[2]);
		
		
	}
	public void tick(){
		y-=10;
		
		anim.runAnimation();
	}
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}
	public double getY(){
		return y;
	}
	public double getX() {
		return x;
	}

}
