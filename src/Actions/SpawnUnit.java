package Actions;

import Buildings.Building;
import Units.Unit;

public class SpawnUnit extends Action {

	private long time;
	private Building building;
	
	public SpawnUnit(Building building, Unit unit, long time) {
		super(unit, true);
		this.building = building;
		this.time = time;
	}

	@Override
	public void endAction() {
		System.out.println("SPAWN!");
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOver() {
		return System.nanoTime() - this.getStartTime() > time;
	}

	@Override
	public void performAction() {
		if(!started) {
			performActionStartup();
		} else {
			building.setBuildTime((System.nanoTime() - this.getStartTime()) / (double)time);
		}
	}

	@Override
	public void performActionStartup() {
		started = true;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
