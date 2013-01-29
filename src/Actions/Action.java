package Actions;

import javax.swing.Icon;

import Units.Unit;

public abstract class Action {

	protected long castTime;
	protected long startTime;
	protected boolean isInstant;
	protected boolean started;
	protected Unit unit;
	
	public Action(Unit unit, long castTime) {
		this.castTime = castTime;
		this.isInstant = false;
		this.started = false;
		this.unit = unit;
	}
	
	public Action(Unit unit, boolean isInstant) {
		this.isInstant = isInstant;
		this.started = false;
		this.unit = unit;
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public long getCastTime() {
		return castTime;
	}
	
	public boolean isReady() {
		return isInstant || System.nanoTime() - startTime >= castTime;
	}
	
	public abstract boolean isOver();
	
	public abstract void performActionStartup();
	
	public abstract void performAction();

	public abstract void endAction();
	
	public abstract void reset();

	public abstract void invalidate();

	
}
