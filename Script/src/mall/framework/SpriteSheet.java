package mall.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet { //breaking down images så att man får den man vill
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = image.getSubimage((col * width)-width, (row*height)-height, width, height);
		return img;
	}

}
