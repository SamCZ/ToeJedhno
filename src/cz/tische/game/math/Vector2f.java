package cz.tische.game.math;

import java.util.Objects;

public class Vector2f {
	
	public float x;
	public float y;
	
	public Vector2f() {
	}
	
	public Vector2f(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Vector2f) {
			Vector2f vec = (Vector2f) o;
			if(vec.x == this.x && vec.y == this.y) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.x, this.y);
	}
	
}
