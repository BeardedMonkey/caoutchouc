package GameObjects;

import java.util.LinkedList;

import Units.Collector;

import Game.Animation;
import Game.State;

public class ManaCrystal extends ClickableObject {

	private LinkedList<Collector> assignedCollectors;
	
	public ManaCrystal(int x, int y, Animation animation) {
		super(x, y, animation);
		this.state = State.STOPPED;
		this.assignedCollectors = new LinkedList<Collector>();
	}
	
	public void addCollector(Collector collector) {
		assignedCollectors.add(collector);
		state = State.PERFORMING_ACTION;
	}
	
	public void removeCollector(Collector collector) {
		assignedCollectors.remove(collector);
		if(assignedCollectors.size() == 0) {
			state = State.STOPPED;
		}
	}
	
	public int getNbAssignedCollectors() {
		return assignedCollectors.size();
	}

	@Override
	public void setObjectBehind(GameObject object) {
		// TODO Auto-generated method stub
		
	}
	
}
