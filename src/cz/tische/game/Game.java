package cz.tische.game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import cz.tische.game.render.FrameBuffer;
import cz.tische.game.render.Renderer;

public abstract class Game {
	
	private JFrame window;
	private Renderer renderer;
	
	public abstract void init();
	public abstract void update();
	public abstract void render(FrameBuffer fb);
	public abstract void onResize(int w, int h);
	
	public void start() {
		this.window = new JFrame("Mhoje hra");
		this.window.setSize(this.createSize());
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLocationRelativeTo(null);
		
		this.window.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent evt) {
				Component cmp = (Component) evt.getSource();
				Game.this.onResize(cmp.getWidth(), cmp.getHeight());
			}
		});
		
		this.renderer = new Renderer(this, this.window.getSize());
		this.window.getContentPane().add(this.renderer);
		this.window.setVisible(true);
		this.renderer.start();
	}
	private Dimension createSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int percent = 70;
		float dx = (float) (screenSize.getWidth() / 100f);
		float dy = (float) (screenSize.getHeight() / 100f);
		return new Dimension((int)(dx * percent), (int)(dy * percent));
	}
	
}
