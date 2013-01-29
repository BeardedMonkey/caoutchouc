package Units;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Abilities.Ability;
import Actions.Action;
import Actions.ActionList;
import Actions.Move;
import Bars.Bar;
import Bars.EnergyBar;
import Bars.MagicBar;
import Buildings.ManaAltar;
import Game.Animation;
import Game.Path;
import Game.PathSegment;
import GameObjects.ManaCrystal;
import GameObjects.SelectableObject;
import Game.State;
import Geometry.Point;
import Geometry.Point;
import Geometry.Rect;

public abstract class Unit extends SelectableObject {

	protected boolean isSelected, isMoving, hasFlying;
	protected int spriteFrame;
	protected Path movingPath;
	protected EnergyBar energyBar;
	protected Rect rectangle;
	protected BufferedImage faceSprite;
	protected int width, height;
	protected Action currentAction;
	
	protected ActionList actionList;


	public Unit(int x, int y, int width, int height, boolean hasFlying, Animation animation) {
		super(x, y, width, height, animation);
		this.movingPath = null;
		this.actionList = new ActionList();
		currentAction = null;
		setAbilities();
	}

	public Unit(int x, int y, boolean hasFlying, Animation animation) {
		super(x, y, animation);
		this.movingPath = null;
		this.actionList = new ActionList();
		currentAction = null;
		setAbilities();
	}

	public Unit getCopy() {
		return null;
	}
	
	protected abstract void setAbilities();

	public BufferedImage getFace() {
		return faceSprite;
	}
	
	public boolean hasFlying() {
		return hasFlying;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rect getRect() {
		return rectangle;
	}

	public void setFlying(boolean hasFlying) {
		this.hasFlying = hasFlying;
	}
	
	public void setPath(Point destination) {
		isMoving = false;
		actionList.clear();
		currentAction = null;
		actionList.addAction(new Move(this, destination));
		//state = getDirectionState(movingPath.getFirst().getDest());
	}
	
	public abstract void goToCrystal(ManaCrystal manaCrystal, ManaAltar closestManaAltar); 

	public ActionList getActionList() {
		return actionList;
	}

	public void changeState(State newState) {
		spriteFrame = 0;
		state = newState;
	}

	public boolean isInRectangle(int x, int y, int width, int height) {
		return super.getIntX() >= x && super.getIntY() >= y && Math.abs(super.getIntX() - x) <= width && Math.abs(super.getIntY() - y) <= height;
	}

	public State getMovingDirectionState(Point destination) {
		Point position = new Point(super.getIntX(), super.getIntY());
		if(Point.insideAngle(Point.DOWN_LEFT, Point.DOWN_RIGHT, position, destination)) { // Move up
			return State.MOVE_UP;
		} else if(Point.insideAngle(Point.DOWN_RIGHT, Point.UP_RIGHT, position, destination)) { // Move right
			return State.MOVE_RIGHT;
		} else if(Point.insideAngle(Point.UP_RIGHT, Point.UP_LEFT, position, destination)) { // Move down
			return State.MOVE_DOWN;
		} else { // Move left
			return State.MOVE_LEFT;
		}
	}

	public State getStoppedDirectionState(Point point) {
		Point position = new Point(super.getIntX(), super.getIntY());
		if(Point.insideAngle(Point.DOWN_LEFT, Point.DOWN_RIGHT, position, point)) { // Face up
			return State.STOPPED_UP;
		} else if(Point.insideAngle(Point.DOWN_RIGHT, Point.UP_RIGHT, position, point)) { // Face right
			return State.STOPPED_RIGHT;
		} else if(Point.insideAngle(Point.UP_RIGHT, Point.UP_LEFT, position, point)) { // Face down
			return State.STOPPED_DOWN;
		} else { // Face left
			return State.STOPPED_LEFT;
		}
	}
	
	public State getCorrespondingStoppedState() {
		if(state == State.MOVE_DOWN) {
			return state.STOPPED_DOWN;
		} else if(state == State.MOVE_LEFT) {
			return state.STOPPED_LEFT;
		} else if(state == State.MOVE_RIGHT) {
			return state.STOPPED_RIGHT;
		} else {
			return state.STOPPED_UP;
		}
	}

	
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public void updateAction() {
		if(actionList.hasAction() || currentAction != null) {
			if(currentAction == null) {
				currentAction = actionList.poolCurrentAction();
				currentAction.setStartTime(System.nanoTime());
			} else {
				if(currentAction.isOver()) {
					currentAction.endAction();
					currentAction = null;
				} else if(currentAction.isReady()) {
					currentAction.performAction();
				}
			}
		}
	}
	
	public void addAction(Action action) {
		actionList.addAction(action);
	}
	
	public Action getCurrentAction() {
		return currentAction;
	}
	
	public double getCastingPercentage() {
		return (System.nanoTime() - currentAction.getStartTime()) / (double)currentAction.getCastTime();
	}
	
	public void kill() {
		state = State.DEAD;
	}

}
