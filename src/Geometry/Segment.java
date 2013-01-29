package Geometry;

public class Segment {

	public Point P, Q;
	public Point[] endPoints;
	
	public Segment(Point P, Point Q) {
		this.P = P;
		this.Q = Q;
		endPoints = new Point[] {P, Q};
	}
	
	public Segment(int x1, int y1, int x2, int y2) {
		P = new Point(x1, y1);
		Q = new Point(x2, y2);
		endPoints = new Point[] {P, Q};
	}
	
}
