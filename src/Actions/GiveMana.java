package Actions;

import Units.Collector;
import Units.Unit;

public class GiveMana extends Action {

	public GiveMana(Unit unit) {
		super(unit, true);
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
		Collector collector = (Collector)unit;
		System.out.println(collector.getCollectingBar().getValue());
		collector.setMana(0);
	}

	@Override
	public void reset() {}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}

}
