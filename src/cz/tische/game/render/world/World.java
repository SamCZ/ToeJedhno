package cz.tische.game.render.world;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cz.tische.game.entity.Entity;
import cz.tische.game.math.SMPLNoise;
import cz.tische.game.math.Vector2f;
import cz.tische.game.render.FrameBuffer;
import cz.tische.game.render.Texture;

public class World {
	
	public Map<Vector2f, Chunk> chunks = new HashMap<Vector2f, Chunk>();
	
	public static int CHUNK_SIZE_X = 36;
	public static int CHUNK_SIZE_Y = 72;
	public static int BLOCK_SIZE = 10;
	
	private Entity player;
	
	private Texture block;
	private Texture grass;
	private float xMove;
	
	public World(Entity player) {
		this.player = player;
	}
	
	public void init() {
		//int gridX = (int) (this.player.location.x / CHUNK_SIZE_X);
		
		this.block = new Texture("res/brown-dirt2.jpg");
		this.grass = new Texture("res/grass.png");
	}
	
	public void update() {
		this.chunks.clear();
		for(int xx = 0; xx < 4; xx ++) {
			Chunk chunk = new Chunk(xx, 0, CHUNK_SIZE_X, CHUNK_SIZE_Y);
			
			for(int x = 0; x < CHUNK_SIZE_X; x++) {
				float hh = 0;
				float scale = 10.0f;
				{
					float persistence = 0.1f;
					float frequency = 0.0006f;
					int octaves = 6;
					float s = 100.0f;
					int loBound = 0;
					int hiBound = 50;
					int rnd = (int)xMove;
					hh += SMPLNoise.scaled_octave_noise_2d(octaves, persistence, 1.0f, loBound, hiBound, ((x + rnd) + (xx * CHUNK_SIZE_X)) / s, 0);
				}
				
				int height = Math.round(hh);
				if(height > CHUNK_SIZE_Y) {
					height = CHUNK_SIZE_Y;
				}
				
				int y;
				for(y = 0; y < height; y++) {
					chunk.set(x, CHUNK_SIZE_Y - y - 1, 1);
				}
				chunk.set(x, CHUNK_SIZE_Y - y - 1, 2);
			}
			
			this.chunks.put(new Vector2f(xx, 0),  chunk);
		}
		xMove += 0.01f;
	}
	
	public void addEntity(Entity e) {
		
	}
	
	public void render(FrameBuffer fb) {
		fb.fillRect(0, 0, fb.getWidth(), fb.getHeight(), Color.white);
		for(Entry<Vector2f, Chunk> e : this.chunks.entrySet()) {
			Chunk chunk = e.getValue();
			int xx = chunk.getX() * CHUNK_SIZE_X * BLOCK_SIZE;
			int yy = chunk.getY() * CHUNK_SIZE_Y * BLOCK_SIZE;
			for(int x = 0; x < CHUNK_SIZE_X; x++) {
				for(int y = 0; y < CHUNK_SIZE_Y; y++) {
					int blockId = chunk.get(x, y);
					if(blockId > 0) {
						Texture texture;
						if(blockId == 1) {
							texture = this.block;
						} else {
							texture = this.grass;
						}
						fb.drawRect(xx, yy, CHUNK_SIZE_X * BLOCK_SIZE, CHUNK_SIZE_Y * BLOCK_SIZE, Color.RED);
						fb.drawImage(texture, xx + (x * BLOCK_SIZE), yy + (y * BLOCK_SIZE), BLOCK_SIZE, BLOCK_SIZE);
					}
				}
			}
		}
	}
	
}
