package Geometry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;

public class VisibilityGraph {

	//private LinkedList<SegmentI> segments;
	private LinkedList<Poly> polygons;
	private HashMap<Point, HashSet<Point>> graph;

	public VisibilityGraph() {
		//segments = new LinkedList<SegmentI>();
		graph = new HashMap<Point, HashSet<Point>>();
		polygons = new LinkedList<Poly>();
	}
	
	public LinkedList<Segment> getEdges() {
		LinkedList<Segment> edges = new LinkedList<Segment>();
		for(Entry<Point, HashSet<Point>> e: graph.entrySet()) {
			for(Point P : e.getValue()) {
				edges.add(new Segment(e.getKey(), P));
			}
		}
		return edges;
	}

	public void connect(Point orig, Point dest) {
		if(graph.get(orig) == null) {
			HashSet<Point> adjacent = new HashSet<Point>();
			adjacent.add(dest);
			graph.put(orig, adjacent);
		} else {
			graph.get(orig).add(dest);
		}
	}
	
	public LinkedList<Poly> getPolygons() {
		return polygons;
	}

	public void addPolygon(Poly newPolygon) {
		// Remove from the graph the edges that intersect this polygon
		// We remove them even if the intersection is at a vertex
		// because those edges must be splitted.
		for(Entry<Point, HashSet<Point>> e : graph.entrySet()) {
			LinkedList<Point> toRemove = new LinkedList<Point>();
			for(Point P : e.getValue()) {
				Segment s1 = new Segment(e.getKey(), P);
				for(int i = 0; i < newPolygon.size(); i++) {
					Segment s2 = new Segment(newPolygon.get(i), newPolygon.get(i + 1));
					if(Geometry.intersects(s1, s2) != Geometry.DOES_NOT_INTERSECT) {
						toRemove.add(P);
					}
				}
			}
			for(Point P : toRemove) {
				e.getValue().remove(P);
			}
		}
		// Add the polygon vertices as graph vertices
		for(int i = 0; i < newPolygon.size(); i++) {
			addVertice(newPolygon.get(i));
		}
		// Add the polygon edges as graph edges
		for(int i = 0; i < newPolygon.size(); i++) {
			connect(newPolygon.get(i), newPolygon.get(i + 1));
			connect(newPolygon.get(i + 1), newPolygon.get(i));
		}
		// Connect the newPolygon vertices to the visible vertices
		for(int i = 0; i < newPolygon.size(); i++) {
			for(Poly pol1 : polygons) {
				for(int j = 0; j < pol1.size(); j++) {
					boolean areVisible = true;
					Segment s = new Segment(newPolygon.get(i), pol1.get(j));
					if(Geometry.countVertices(newPolygon, s) > 1) {
						areVisible = false;
						break;
					}
					if(Geometry.countVertices(pol1, s) > 1) {
						areVisible = false;
						break;
					}
					
					for(Poly pol2 : polygons) {
						if(Geometry.intersects(pol2, s) == Geometry.INTERSECTS) {
							areVisible = false;
							break;
						}
					}
					if(Geometry.intersects(newPolygon, s) == Geometry.INTERSECTS) {
						areVisible = false;
						break;
					}
					if(areVisible) {
						connect(newPolygon.get(i), pol1.get(j));
						connect(pol1.get(j), newPolygon.get(i));
					}
				}
			}
		}
		polygons.add(newPolygon);
	}

	// Return true if the point is a new vertex
	public boolean addVertice(Point p) {
		HashSet<Point> adjacent = graph.get(p);
		if(adjacent == null) {
			adjacent = new HashSet<Point>();
			return true;
		}
		return false;
	}

	
	/*
	public void addSegment(SegmentI newSegment) {
		segments.add(newSegment);
		// Compute visibility from this segment extremities to the others
		for(PointI p1 : newSegment.endPoints) {
			for(SegmentI seg1 : segments) {
				for(PointI p2 : seg1.endPoints) {
					SegmentI visibilitySeg = new SegmentI(p1, p2);
					boolean isVisible = true;
					for(SegmentI seg2 : segments) {
						// TODO: If the intersection is and end point it is ok.
						if(GeometryI.intersects(visibilitySeg, seg2) != GeometryI.DOES_NOT_INTERSECT) {
							isVisible = false;
							break;
						}
					}
					if(isVisible) { // Connect p1 and p2
						connect(p1, p2);
						connect(p2, p1);
					}
				}
			}	
		}

	}
	*/

}
