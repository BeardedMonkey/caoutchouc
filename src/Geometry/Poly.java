package Geometry;

import java.awt.Polygon;
import java.util.ArrayList;


public class Poly {

	public ArrayList<Point> vertices;
	
	public Poly(int nbVertices) {
		this.vertices = new ArrayList<Point>(nbVertices);
	}
	
	public Poly() {
		this.vertices = new ArrayList<Point>();
	}
	
	public void addVertex(Point p) {
		vertices.add(p);
	}
	
	public boolean isFirstVertex(Point p) {
		return size() > 0 && vertices.get(0).equals(p);
	}
	
	public int size() {
		return vertices.size();
	}
	
	public Point get(int i) {
		return vertices.get(i % size());
	}
	
	public Polygon getPolygon() {
		Polygon polygon = new Polygon();
		for(Point P : vertices) {
			polygon.addPoint(P.ix, P.iy);
		}
		return polygon;
	}
	
	public String toString() {
		return vertices.toString();
	}
	
}
