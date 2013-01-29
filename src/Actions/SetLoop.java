package Actions;

import Units.Unit;

public class SetLoop extends Action {

	private boolean loop;
	
	public SetLoop(Unit unit, boolean loop) {
		super(unit, true);
		this.loop = loop;
	}

	@Override
	public void endAction() {
		unit.getActionList().setLoop(loop);
	}

	@Override
	public boolean isOver() {
		return true;
	}

	@Override
	public void performAction() {}

	@Override
	public void performActionStartup() {}

	@Override
	public void reset() {}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}

}
