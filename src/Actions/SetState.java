package Actions;

import Game.State;
import GameObjects.AnimatedObject;
import Units.Unit;

public class SetState extends Action {

	private AnimatedObject target;
	private State state;
	
	public SetState(Unit unit, AnimatedObject target, State state) {
		super(unit, true);
		this.target = target;
		this.state = state;
	}

	@Override
	public void endAction() {
		target.setState(state);
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
