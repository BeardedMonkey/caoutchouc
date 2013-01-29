package Actions;

import javax.swing.Icon;

import Units.Unit;
import Game.PathSegment;
import Geometry.Point;
import Geometry.Point;

public class Move extends Action {

	private PathSegment pathSegment;
	private Point destination;
	private int tol = 4;
	
	public Move(Unit unit, Point destination) {
		super(unit, true);
		this.destination = destination;
		pathSegment = computePathSegment(unit.getIntX(), unit.getIntY(), destination.ix, destination.iy);
	}
	
	public PathSegment getPathSegment() {
		return pathSegment;
	}
	
	@Override
	public boolean isOver() {
		// TODO: Think of a better way to do this.
		return Math.abs(unit.getIntX() - destination.x) <= tol && 
			   Math.abs(unit.getIntY() - destination.y) <= tol;
	}
	
	@Override
	public void performActionStartup() {
		unit.setMoving(true);
		unit.setState(unit.getMovingDirectionState(pathSegment.getDest()));
		started = true;
	}

	@Override
	public void performAction() {
		if(!started) {
			performActionStartup();
		} else {
			unit.addX(pathSegment.getDir().x);
			unit.addY(pathSegment.getDir().y);			
		}
	}

	@Override
	public void endAction() {
		unit.setState(unit.getCorrespondingStoppedState());
	}
	
	public String toString() {
		return "move";
	}

	@Override
	public void reset() {
		started = false;
		pathSegment = computePathSegment(unit.getIntX(), unit.getIntY(), destination.ix, destination.iy);
	}
	
	private PathSegment computePathSegment(int x, int y, int mx, int my) {
		double dx = mx - x;
		double dy = my - y;
		double norm = Math.sqrt(dx * dx + dy * dy);
		double speed = 1.5;
		Point destination = new Point(mx, my);
		Point direction = new Point((speed * dx) / norm, (speed * dy) / norm);
		return new PathSegment(direction, destination);
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}


}
