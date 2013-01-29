package Game;

import Geometry.Point;

public class PathSegment {
	
	private Point direction;
	private Point destination;
	
	public PathSegment(Point direction, Point destination) {
		this.direction = direction;
		this.destination = destination;
	}
	
	public Point getDir() {
		return direction;
	}
	
	public Point getDest() {
		return destination;
	}
	
	public String toString() {
		return "[" + direction + "; " + destination + "]";
	}
	
}