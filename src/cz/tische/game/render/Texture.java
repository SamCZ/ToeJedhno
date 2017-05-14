package cz.tische.game.render;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	
	private BufferedImage img;
	
	public Texture(String path) {
		try {
			this.img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImg() {
		return img;
	}
	
}
