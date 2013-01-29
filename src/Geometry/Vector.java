package Geometry;

public class Vector {

	protected int x, y;
	protected double dx, dy;
	protected double norm;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
		norm = Math.sqrt(x * x + y * y);
		dx = x / norm;
		dy = y / norm;
	}
	
	public double getDx() {
		return dx;
	}
	
	public double getDy() {
		return dy;
	}
	
}
