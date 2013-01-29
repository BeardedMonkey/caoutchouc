package Geometry;

import java.util.Comparator;
import java.util.TreeSet;

public class PolarComparator implements Comparator<Point> {
	
	public static void main(String[] args) {
		TreeSet<Point> T = new TreeSet<Point>(new PolarComparator(Point.ORIG));
		T.add(new Point(1, 1));
		T.add(new Point(0, 1));
		T.add(new Point(0, 0));
		T.add(new Point(1, 0));
		System.out.println(T);
	}
	

	private Point R = new Point(1, 0);
	private Point C; // Point that represents the center of the order (counter-clockwise).

	public PolarComparator(Point center) {
		C = center;
	}

	@Override
	public int compare(Point P, Point Q) { // NOT OK
		double RxP = Geometry.crossProd(R, P);
		double RxQ = Geometry.crossProd(R, Q);
		if(P.equals(Q)) {
			return 0;
		} else if(P.equals(Point.ORIG)) {
			return -1;
		} else if(Q.equals(Point.ORIG)) {
			return 1;
		}
		if(P.y == 0 && Q.y == 0) { // Both are on the x axis.
			if(Math.signum(P.x) == Math.signum(Q.x)) { // Check if they are on the same side
				return (int)Math.signum(Q.x - P.x); // The smallest is the one closer to (0, 0)
			} else {
				return -(int)Math.signum(P.x); // They have opposite signs, if P > 0 then P is the smallest and otherwise it is Q
			}
		}
		if(Math.signum(RxP) == 0) {
			return -1;
		} else if(Math.signum(RxQ) == 0) {
			return 1;
		}
		if(Math.signum(RxP) < Math.signum(RxQ)) {
			return 1;
		} else if(Math.signum(RxP) > Math.signum(RxQ)) {
			return -1;
		}
		double PxQ = Geometry.crossProd(R, Q);
		if(PxQ < 0) {
			return 1;
		} else if(PxQ > 0) {
			return -1;
		}
		double distCP = Geometry.squareDistance(C, P);
		double distCQ = Geometry.squareDistance(C, Q);
		if(distCP == distCQ) {
			return 0;
		}
		return (int)Math.signum(distCQ - distCP);
	}
}
