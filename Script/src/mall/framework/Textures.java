package mall.framework;

import java.awt.image.BufferedImage;

import mall.window.BufferedImageLoader;

public class Textures { //ladda alla textures....
	
	SpriteSheet bs, ps;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[14];
	public BufferedImage[] playerJump = new BufferedImage[6];
	
	public Textures(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
		}catch (Exception e){
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		getTextures();
	}
	private void getTextures(){
		block[0] = bs.grabImage(1,1,32,32);
		block[1] = bs.grabImage(2,1,32,32);
		
		//looking right
		player[0] = ps.grabImage(1, 1, 32, 64); //idle player
		player[1] = ps.grabImage(2, 1, 32, 64); //walking
		player[2] = ps.grabImage(3, 1, 32, 64);//walking
		player[3] = ps.grabImage(4, 1, 32, 64);//walking
		player[4] = ps.grabImage(5, 1, 32, 64);//walking
		player[5] = ps.grabImage(6, 1, 32, 64);//walking
		player[6] = ps.grabImage(7, 1, 32, 64);//walking
		
		//looking left
		player[7] = ps.grabImage(20, 1, 32, 64); //idle player
		player[8] = ps.grabImage(19, 1, 32, 64); //walking
		player[9] = ps.grabImage(18, 1, 32, 64);//walking
		player[10] = ps.grabImage(17, 1, 32, 64);//walking
		player[11] = ps.grabImage(16, 1, 32, 64);//walking
		player[12] = ps.grabImage(15, 1, 32, 64);//walking
		player[13] = ps.grabImage(14, 1, 32, 64);//walking
		//player jupming...
		playerJump[0] = ps.grabImage(8, 2, 32, 64);
		playerJump[1] = ps.grabImage(9, 2, 32, 64);
		playerJump[2] = ps.grabImage(10, 2, 32, 64);
		playerJump[3] = ps.grabImage(11, 2, 32, 64);
		playerJump[4] = ps.grabImage(12, 2, 32, 64);
		playerJump[5] = ps.grabImage(13, 2, 32, 64);
	}

}
