package cz.tische.game.render.world;

public class Chunk {
	
	private int x;
	private int y;
	private int sizeX;
	private int sizeY;
	private int[][] data;
	
	public Chunk(int x, int y, int sizeX, int sizeY) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.data = new int[sizeX][sizeY];
	}
	
	public void set(int x, int y, int blockId) {
		if(!this.isOut(x, y)) {
			this.data[x][y] = blockId;
		}
	}
	
	public int get(int x, int y) {
		if(this.isOut(x, y)) {
			return 0;
		}
		return this.data[x][y];
	}
	
	public boolean isOut(int x, int y) {
		return x < 0 || y < 0 || x > this.sizeX-1 || y > this.sizeY-1;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int[][] getData() {
		return data;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}
	
}
