package Actions;

import Units.Unit;

public class Wait extends Action {

	private boolean isOver;
	
	public Wait(Unit unit, int sec) {
		super(unit, (long)(10e8 * sec));
		isOver = false;
	}

	@Override
	public boolean isOver() {
		return isOver;
	}

	@Override
	public void performActionStartup() {}

	@Override
	public void performAction() {
		isOver = true;
	}
	
	@Override
	public void endAction() {}

	public String toString() {
		return "wait";
	}

	@Override
	public void reset() {
		isOver = false;
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}
	
}
