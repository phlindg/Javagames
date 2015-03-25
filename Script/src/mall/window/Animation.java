package mall.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed; //hur snabbt animationen ska g�
	private int frames; //hur m�nga bilder den ska loopa
	
	private int index = 0; //vad vi �r p� nu
	private int count = 0; //vad vi beh�ver va p�
	
	private BufferedImage[] images; //hur m�nga vi har
	private BufferedImage currentImg; // current
	
	public Animation(int speed, BufferedImage... args){ //"..." menas att vi kan ha hur m�nga bilder som helst
		this.speed = speed;
		images = new BufferedImage[args.length];
		
		for (int i = 0; i < args.length; i++){
			images[i] = args[i]; //f�rsta argumentet blir f�rsta bilden, osv.
		}
		frames = args.length;
	}
	public void runAnimation(){
		index++;
		if (index > speed){
			index = 0;
			nextFrame();
		}
	}
	private void nextFrame(){
		for (int i= 0; i<frames; i++){
			if (count == i)
				currentImg = images[i]; //count == what frame we are currently at
		}
		count ++;
		
		if (count > frames){
			count =0; //denna loopar animationen
		}
	}
	public void drawAnimation(Graphics g, int x, int y){
		g.drawImage(currentImg, x, y, null);
	}
	public void drawAnimation(Graphics g, int x, int y, int scalex, int scaley){
		g.drawImage(currentImg, x, y,scalex, scaley, null); //s� att man kan scalea medans man animerar
	}
	

}
