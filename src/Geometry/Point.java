package Geometry;

public class Point {

	public static final Point ORIG = new Point(0, 0);
	public static final Point RIGHT = new Point(1, 0);
	public static final Point UP_RIGHT = new Point(1, 1);
	public static final Point UP = new Point(0, 1);
	public static final Point UP_LEFT = new Point(-1, 1);
	public static final Point LEFT = new Point(-1, 0);
	public static final Point DOWN_LEFT = new Point(-1, -1);
	public static final Point DOWN = new Point(0, -1);
	public static final Point DOWN_RIGHT = new Point(1, -1);
	public static final double eps = 10e-8;
	
	public double x, y;
	public int ix, iy;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		this.ix = (int)x;
		this.iy = (int)y;
	}
	
	public Point(String s) { // s must be in the format: "x y".
		String[] data = s.split(" ");
		ix = Integer.parseInt(data[0]);
		iy = Integer.parseInt(data[1]);
		x = ix;
		y = iy;
	}
	
	public static double crossProd(Point v1, Point v2) {
		return v1.x * v2.y - v1.y * v2.x;
	}
	
	public static Point makeVector(Point from, Point to) {
		return new Point(to.x - from.x , to.y- from.y);
	}
	
	public static boolean equal(Point P, Point Q) {
		return Math.abs(P.x - Q.x) <= eps && Math.abs(P.y - Q.y) <= eps;
	}
	
	public static boolean equal(double x, double y, Point Q) {
		return Math.abs(x - Q.x) <= eps && Math.abs(y - Q.y) <= 1;
	}
	
	public static boolean insideAngle(Point v, Point u, Point P, Point A) {
		Point w = makeVector(P, A);
		double uw = Math.signum(crossProd(u, w));
		double uv = Math.signum(crossProd(u, v));
		double vw = Math.signum(crossProd(v, w));
		double vu = Math.signum(crossProd(v, u));
		if(Double.compare(uw, uv) == 0 &&
		   Double.compare(vw, vu) == 0) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
