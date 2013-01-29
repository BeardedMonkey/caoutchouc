package Game;

public enum State {
	
	MOVE_UP, // Object moving up
	MOVE_DOWN, // Object moving down
	MOVE_LEFT, // Object moving left
	MOVE_RIGHT, // Object moving right
	PERFORMING_ACTION, // Object performing action
	STOPPED_UP, // Object stopped facing up
	STOPPED_DOWN, // Object stopped facing down
	STOPPED_LEFT, // Object stopped facing left
	STOPPED_RIGHT, // Object stopped facing right
	STOPPED, // Object stopped
	DEAD, // Object is dead
	DESTROYED,
	CASTING;
	
	public static State getFromStr(String name) {
		if(name.equals("MOVE_UP")) {
			return MOVE_UP;
		} else if(name.equals("MOVE_DOWN")) {
			return MOVE_DOWN;
		} else if(name.equals("MOVE_LEFT")) {
			return MOVE_LEFT;
		} else if(name.equals("MOVE_RIGHT")) {
			return MOVE_RIGHT;
		} else if(name.equals("PERFORMING_ACTION")) {
			return PERFORMING_ACTION;
		} else if(name.equals("STOPPED_UP")) {
			return STOPPED_UP;
		} else if(name.equals("STOPPED_DOWN")) {
			return STOPPED_DOWN;
		} else if(name.equals("STOPPED_LEFT")) {
			return STOPPED_LEFT;
		} else if(name.equals("STOPPED_RIGHT")) {
			return STOPPED_RIGHT;
		} else if(name.equals("STOPPED")) {
			return STOPPED;
		} else if(name.equals("DEAD")) {
			return DEAD;
		} else if(name.equals("DESTROYED")) {
			return DESTROYED;
		} else if(name.equals("CASTING")) {
			return CASTING;
		}
		
		return null; // Invalid name
	}
}
