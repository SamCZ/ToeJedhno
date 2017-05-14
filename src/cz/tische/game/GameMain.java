package cz.tische.game;

import cz.tische.game.entity.Entity;
import cz.tische.game.entity.Player;
import cz.tische.game.render.FrameBuffer;
import cz.tische.game.render.Texture;
import cz.tische.game.render.world.World;

public class GameMain extends Game {
	
	private World world;
	
	@Override
	public void init() {
		Entity player = new Player(500, 20);
		
		this.world = new World(player);
		this.world.init();
	}

	@Override
	public void update() {
		this.world.update();
	}

	@Override
	public void render(FrameBuffer fb) {
		this.world.render(fb);
	}
	
	public static void main(String[] args) {
		GameMain game = new GameMain();
		game.start();
	}

	@Override
	public void onResize(int w, int h) {
		System.out.println(w + " " + h);
	}

}
