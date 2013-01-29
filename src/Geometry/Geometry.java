package Geometry;

public class Geometry {
	
	public static final int DOES_NOT_INTERSECT = 0;
	public static final int INTERSECTS_AT_ENDPOINT = 1; // There is intersection at a vertex
	public static final int INTERSECTS = 2; // There is intersection but it is not at a vertex
	
	public static int intersects(Segment s1, Segment s2) {
		// Check if the support of s1 separates the end points of s2 and vice-versa.
		if(segmentSuportSeparatesPoints(s1, s2.P, s2.Q) &&
		   segmentSuportSeparatesPoints(s2, s1.P, s1.Q)) {
			return INTERSECTS;
		}
		// Check limit cases where intersection in at an segment end point.
		if(containsPoint(s1, s2.P) || containsPoint(s1, s2.Q) || containsPoint(s2, s1.P) || containsPoint(s2, s1.Q)) {
			return INTERSECTS_AT_ENDPOINT;
		}
		return DOES_NOT_INTERSECT;
	}
	
	public static boolean segmentSuportSeparatesPoints(Segment s, Point P, Point Q) {
		Point v1 = getVectorFromTo(P, s.P);
		Point u1 = getVectorFromTo(P, s.Q);
		Point v2 = getVectorFromTo(Q, s.P);
		Point u2 = getVectorFromTo(Q, s.Q);
		double prodV1U1 = crossProd(v1, u1);
		double prodV2U2 = crossProd(v2, u2);
		return (prodV1U1 < 0 && prodV2U2 > 0) || (prodV1U1 > 0 && prodV2U2 < 0);
	}

	public static boolean containsPoint(Segment s, Point P) {
		return collinear(P, s.P, s.Q) && 
			   Math.min(s.P.x, s.Q.x) <= P.x && P.x <= Math.max(s.P.x, s.Q.x) &&
			   Math.min(s.P.y, s.Q.y) <= P.y && P.y <= Math.max(s.P.y, s.Q.y);
	}
	
	public static boolean isInRectangle(int x, int y, int rectCenterX, int rectCenterY, Rect rectangle) {
		return isInRectangle(x, y, rectangle.getXupLeft(rectCenterX, rectCenterY), rectangle.getYupLeft(rectCenterX, rectCenterY), 
								   rectangle.getXdownRight(rectCenterX, rectCenterY), rectangle.getYdownRight(rectCenterX, rectCenterY));
	}
	
	public static boolean isInRectangle(long x, long y, long x1, long y1, long x2, long y2) {
		return Math.min(x1, x2) <= x && x <= Math.max(x1, x2) &&
			   Math.min(y1, y2) <= y && y <= Math.max(y1, y2);
	}
	
	public static double crossProd(Point v, Point u) {
		return v.x * u.y - v.y * u.x;
	}
	
	public static Point getVectorFromTo(Point P, Point Q) {
		return new Point(Q.x - P.x, Q.y - P.y);
	}

	public static boolean collinear(Point P, Point Q, Point R) {
		Point v = getVectorFromTo(P, Q);
		Point u = getVectorFromTo(P, R);
		return crossProd(v, u) == 0;
	}
	
	public static boolean insideAngleAtP(Point v, Point u, Point P, Point Q) {
		Point w = new Point(Q.x - P.x, Q.y - P.y);
		return Math.signum(crossProd(u, w)) == Math.signum(crossProd(u, v)) &&
			   Math.signum(crossProd(v, w)) == Math.signum(crossProd(v, u));
	}

	public static double squareDistance(Point P, Point Q) {
		return (P.x - Q.x) * (P.x - Q.x) + (P.y - Q.y) * (P.y - Q.y);
	}
	
	// Counts the number of vertices of the polygon the segment contains
	public static int countVertices(Poly p, Segment s) {
		int count = 0;
		for(int i = 0; i < p.size(); i++) {
			if(containsPoint(s, p.get(i))) {
				count++;
			}
		}
		return count;
	}

	public static int intersects(Poly p, Segment s) {
		boolean endPointIntersection = false;
		for(int i = 0; i < p.size(); i++) {
			Segment cur = new Segment(p.get(i), p.get(i + 1));
			int intersectCode = Geometry.intersects(cur, s);
			if(intersectCode == INTERSECTS) {
				return intersectCode;
			} else if(intersectCode == INTERSECTS_AT_ENDPOINT) {
				endPointIntersection = true;
			}
		}
		if(endPointIntersection) {
			return INTERSECTS_AT_ENDPOINT;
		}
		return DOES_NOT_INTERSECT;
	}
	
	public static Point rotate(Point p, double a) {
		return new Point(p.x * Math.cos(a) - p.y * Math.sin(a), p.x * Math.sin(a) + p.y * Math.cos(a));
	}
	
}
