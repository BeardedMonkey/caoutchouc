package GameObjects;

import Geometry.Point;
import Geometry.Rect;

public class GameObject {

	protected double x, y;
	protected int width, height;
	protected Point point;
	
	public GameObject(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.point = new Point((int)x, (int)y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void addX(double dx) {
		x += dx;
	}

	public void addY(double dy) {
		y += dy;
	}

	public int getIntX() {
		return (int)x;
	}

	public int getIntY() {
		return (int)y;
	}
	

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public Rect getRectangle() {
		return new Rect(width, height);
	}
	


}
