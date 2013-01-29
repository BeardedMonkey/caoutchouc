package Actions;

import javax.swing.Icon;

import Units.Unit;
import Geometry.Point;
import Geometry.Point;

public class Look extends Action {

	private Point direction;

	public Look(Unit unit, Point direction) {
		super(unit, true);
		this.direction = direction;
	}

	@Override
	public boolean isOver() {
		return true;
	}

	@Override
	public void performActionStartup() {}

	@Override
	public void performAction() {}

	@Override
	public void endAction() {
		unit.setState(unit.getStoppedDirectionState(direction));
	}
	
	public String toString() {
		return "look";
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}

}
