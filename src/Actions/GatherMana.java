package Actions;

import Game.State;
import GameObjects.ManaCrystal;
import Units.Collector;
import Units.Unit;

public class GatherMana extends Action {

	private long time;
	private double initialAmount;
	private ManaCrystal manaCrystal;
	
	public GatherMana(Unit unit, ManaCrystal manaCrystal, long time) {
		super(unit, true);
		this.time = time;
		this.manaCrystal = manaCrystal;
	}

	@Override
	public boolean isOver() {
		return System.nanoTime() - this.getStartTime() > time;
	}

	@Override
	public void performActionStartup() {
		Collector collector = (Collector)unit;
		manaCrystal.addCollector((Collector)unit);
		initialAmount = collector.getCollectingBar().getPercentage();
		started = true;
	}

	@Override
	public void performAction() {
		if(!started) {
			performActionStartup();
		} else {
			manaCrystal.setState(State.PERFORMING_ACTION);
			Collector collector = (Collector)unit;
			collector.setMana(initialAmount + (System.nanoTime() - this.getStartTime()) / (double)time);
		}
	}
	
	@Override
	public void endAction() {
		manaCrystal.removeCollector((Collector)unit);
	}

	public String toString() {
		return "gather mana";
	}

	@Override
	public void reset() {
		Collector collector = (Collector)unit;
		initialAmount = 0.0;
		started = false;
		time = collector.getRemainingTime();
	}

	@Override
	public void invalidate() {
		manaCrystal.removeCollector((Collector)unit);
	}
	
}
