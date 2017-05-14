package cz.tische.game.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FrameBuffer {
	
	private int width;
	private int height;
	private BufferedImage image;
	private Graphics2D graphics;
	
	public FrameBuffer(int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.graphics = this.image.createGraphics();
	}
	
	public void drawPixel(int x, int y, Color color) {
		this.graphics.setColor(color);
		this.graphics.drawLine(x, y, x, y);
	}
	
	public void drawLine(int x1, int y1, int x2, int y2, Color color) {
		this.graphics.setColor(color);
		this.graphics.drawLine(x1, y1, x2, y2);
	}
	
	public void drawRect(int x, int y, int width, int height, Color color) {
		this.graphics.setColor(color);
		this.graphics.drawRect(x, y, width, height);
	}
	
	public void fillRect(int x, int y, int width, int height, Color color) {
		this.graphics.setColor(color);
		this.graphics.fillRect(x, y, width, height);
	}
	
	public void drawImage(Texture texture, int x, int y) {
		this.graphics.drawImage(texture.getImg(), x, y, null);
	}
	
	public void drawImage(Texture texture, int x, int y, int width, int height) {
		this.graphics.drawImage(texture.getImg(), x, y, width, height, null);
	}
	
	public void drawFrameBuffer(FrameBuffer fb, int x, int y, int width, int height) {
		this.graphics.drawImage(fb.getImage(), x, y, width, height, null);
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public Graphics2D getGraphics() {
		return graphics;
	}
	
}
