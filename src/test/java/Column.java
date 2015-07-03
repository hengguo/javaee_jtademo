	    				

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
/**
 * x, y 是空隙中间位置. 
 */
public class Column {
	BufferedImage image;
	//以柱子的中间作为柱子的位置
	int x;
	int y; // 140 ~ 280
	int width;
	int height;
	int gap = 109;
	Random r = new Random();
	
	public Column(int x) throws IOException { 
		image = ImageIO.read(getClass().getResource("column.png"));
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = r.nextInt(140) + 140;
	}
	
	public void step(){
		x--;
		if(x<-width){
			x = 320;
			this.y = r.nextInt(140) + 140;
		}
	}
	public void paint(Graphics g){
		//g.drawRect(x-width/2, y-height/2, width, height/2-gap/2);
		//g.drawRect(x-width/2, y+gap/2, width, height/2-gap/2);
		g.drawImage(image, x-width/2, y-height/2, null);
	}
}

	    			