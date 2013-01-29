package GameObjects;

import java.awt.image.BufferedImage;

import Game.Animation;
import Game.State;
import Game.Tuple;

public abstract class AnimatedObject extends GameObject {

	protected Animation animation;
	protected int spriteFrame;
	protected State state;
	protected boolean disposeAfterAnimation;
	protected boolean disposed;
	
	public AnimatedObject(int x, int y, int width, int height, Animation animation) {
		super(x, y, width, height);
		this.animation = animation;
		this.spriteFrame = 0;
		this.state = State.STOPPED;
		this.disposeAfterAnimation = false;
		this.disposed = false;
	}
	
	public AnimatedObject(int x, int y, Animation animation) {
		super(x, y, animation.getWidth(), animation.getHeight());
		this.animation = animation;
		this.spriteFrame = 0;
		this.state = State.STOPPED;
		this.disposeAfterAnimation = false;
		this.disposed = false;
	}
	
	public AnimatedObject(int x, int y, Animation animation, boolean disposeAfterAnimation, State state) {
		super(x, y, animation.getWidth(), animation.getHeight());
		this.animation = animation;
		this.spriteFrame = 0;
		this.state = state;
		this.disposeAfterAnimation = disposeAfterAnimation;
		this.disposed = false;
	}
	
	public AnimatedObject(int x, int y, Animation animation, State state) {
		super(x, y, animation.getWidth(), animation.getHeight());
		this.animation = animation;
		this.spriteFrame = 0;
		this.state = state;
		this.disposeAfterAnimation = false;
		this.disposed = false;
	}
	
	public boolean disposed() {
		return disposed;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return this.state;
	}
	
	
	public void updateGraphics() {
		Tuple<BufferedImage[], Integer> stateSprites = this.animation.getStateSprites(this.state);
		BufferedImage[] spriteFrames = stateSprites.getX();
		if(disposeAfterAnimation && this.spriteFrame + 1 == spriteFrames.length * stateSprites.getY()) {
			disposed = true;
		} else {
			this.spriteFrame = (this.spriteFrame + 1) % (spriteFrames.length * stateSprites.getY());			
		}
	}
	
	public BufferedImage getSprite() {
		Tuple<BufferedImage[], Integer> stateSprites = this.animation.getStateSprites(this.state);
		return stateSprites.getX()[this.spriteFrame / stateSprites.getY()];
	}
	
	public abstract void setObjectBehind(GameObject object);
	
}
