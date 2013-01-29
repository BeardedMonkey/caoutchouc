package Game;
import java.util.ArrayList;

import Geometry.Point;


public class Path {

	private ArrayList<PathSegment> path;
	private int segmentIndex;
	
	public Path() {
		path = new ArrayList<PathSegment>();
		segmentIndex = 0;
	}
	
	public void add(Point direction, Point destination) {
		path.add(new PathSegment(direction, destination));
	}
	
	public boolean goToNext() {
		segmentIndex++;
		return segmentIndex >= path.size(); // Return if that path is over or not
	}
	
	public PathSegment getCurrent() {
		return path.get(segmentIndex);
	}
	
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(PathSegment pathSegment : path) {
			sb.append(pathSegment.toString());
		}
		sb.append(">");
		return sb.toString();
	}
	
}
