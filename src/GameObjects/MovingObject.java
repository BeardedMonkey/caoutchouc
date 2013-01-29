package GameObjects;

import Game.Animation;
import Game.State;
import Geometry.Vector;

public abstract class MovingObject extends AnimatedObject {

	protected Vector v;
	protected boolean colides;
	protected int ttl, speed;
	
	public MovingObject(int x, int y, Animation animation, Vector v, int speed, boolean colides, int ttl) {
		super(x, y, animation, State.PERFORMING_ACTION);
		this.v = v;
		this.speed = speed;
		this.colides = colides;
		this.ttl = ttl;
	}

	@Override
	public void setObjectBehind(GameObject object) {}
	
	public void updatePosition() {
		x = x + v.getDx() * speed;;
		y = y + v.getDy() * speed;
	}

	public abstract void colide(GameObject go);
	
}
