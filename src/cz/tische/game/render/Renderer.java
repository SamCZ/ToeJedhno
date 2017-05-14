package cz.tische.game.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import cz.tische.game.Game;

public class Renderer extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private boolean isRunning = true;
	private Game game;
	private FrameBuffer fb;
	
	public static double UPS = 1000.0D;
	public static double FPS = 60.0D;
	
	public Renderer(Game game, Dimension dimension) {
		this.game = game;
		this.setSize(dimension);
	}
	
	@Override
	public void run() {
		this.game.init();
		
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();
		
		this.fb = new FrameBuffer(this.getWidth(), this.getHeight());
		
		while(this.isRunning) {
			long currentTime = System.nanoTime();
	        deltaU += (currentTime - initialTime) / timeU;
	        deltaF += (currentTime - initialTime) / timeF;
	        initialTime = currentTime;
			
	        if (deltaU >= 1) {
	           this.game.update();
	            ticks++;
	            deltaU--;
	        }

	        if (deltaF >= 1) {
	            render();
	            frames++;
	            deltaF--;
	        }
	        
	        if (System.currentTimeMillis() - timer > 1000) {
	            if (false) {
	                System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
	            }
	            frames = 0;
	            ticks = 0;
	            timer += 1000;
	        }
		}
	}
	
	private void render() {
		BufferStrategy buffer = this.getBufferStrategy();
		if(buffer == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		this.game.render(this.fb);
		g.drawImage(this.fb.getImage(), 0, 0, this);
		g.dispose();
		buffer.show();
	}

	public void start() {
		new Thread(this).start();
	}
	
	public void stop() {
		this.isRunning = false;
	}

}
