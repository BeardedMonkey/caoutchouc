package Actions;

import Units.Unit;

import Game.Animation;
import Game.Main;
import Game.State;
import GameObjects.AbilityAnimation;

public class PerformAnimation extends Action {

	private Main main;
	private Animation animation;
	private int x, y;
	
	public PerformAnimation(Unit unit, Main main, Animation animation, int x, int y) {
		super(unit, true);
		this.main = main;
		this.animation = animation;
		this.x = x;
		this.y = y;
	}

	@Override
	public void endAction() {
		main.addAnimatedObject(new AbilityAnimation(x, y, animation, true, State.PERFORMING_ACTION));
		main.clearSelectedAbility();
	}

	@Override
	public void invalidate() {}

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

}
