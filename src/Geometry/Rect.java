package Geometry;

public class Rect {

	private int width;
	private int height;
	
	public Rect(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getXupLeft(int xCenter, int yCenter) {
		return xCenter - width / 2;
	}
	
	public int getYupLeft(int xCenter, int yCenter) {
		return yCenter - height / 2;
	}
	
	public int getXdownRight(int xCenter, int yCenter) {
		return xCenter + width / 2;
	}
	
	public int getYdownRight(int xCenter, int yCenter) {
		return yCenter + height / 2;
	}
	
	public String toString() {
		return width + " " + height;
	}
	
}
