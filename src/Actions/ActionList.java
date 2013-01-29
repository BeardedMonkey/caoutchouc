package Actions;

import java.util.LinkedList;
import java.util.Queue;

public class ActionList {

	private Queue<Action> actions;
	private boolean loop;
	
	public ActionList() {
		this.actions = new LinkedList<Action>();
		this.loop = false;
	}
	
	public ActionList(boolean loop) {
		this.actions = new LinkedList<Action>();
		this.loop = loop;
	}
	
	public void addAction(Action action) {
		this.actions.add(action);
	}
	
	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	
	public boolean hasAction() {
		return !actions.isEmpty();
	}
	
	public Action getCurentAction() {
		return actions.peek();
	}
	
	public void clear() {
		for(Action action : actions) {
			action.invalidate();
		}
		actions.clear();
	}
	
	public int size() {
		return actions.size();
	}
	
	public Action poolCurrentAction() {
		if(actions.isEmpty()) {
			return null;
		}
		Action next = actions.poll();
		if(loop) {
			next.reset();
			actions.add(next);
		}
		return next;
	}
	
	public String toString() {
		return actions.toString() + (loop ? " loop "  : "");
	}
	
}
