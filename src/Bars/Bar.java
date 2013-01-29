package Bars;

import GameObjects.GameObject;

import java.awt.Color;

public abstract class Bar {

	protected long maxValue;
	protected long curValue;
	protected double percentage;
	
	public Bar(long maxValue) {
		this.maxValue = maxValue;
		this.percentage = 1.0;
		this.curValue = maxValue;
	}
	
	public Bar(long maxValue, long startValue) {
		this.maxValue = maxValue;
		this.curValue = startValue;
		normalizePercentage();
	}
	
	public void decrease(long amount) {
		curValue = Math.max(0, curValue - amount);
		normalizePercentage();
	}
	
	public void increase(long amount) {
		curValue = Math.min(maxValue, curValue + amount);
		normalizePercentage();
	}
	
	public void normalizePercentage() {
		percentage = (1.0 * curValue) / maxValue;
		if(percentage > 0.99) {
			percentage = 1.0; 
		}
	}
	
	public void setPercentage(double percentage) {
		this.percentage = Math.min(1.0, percentage);
		if(this.percentage > 0.99) {
			this.percentage = 1.0; 
		}
		curValue = Math.min((long)Math.ceil(percentage * maxValue), maxValue);
	}
	
	public boolean isEmpty() {
		return curValue == 0;
	}
	
	public double getPercentage() {
		return percentage;
	}
	
	public long getValue() {
		return curValue;
	}
	
	public long getMaxValue() {
		return maxValue;
	}
	
	public abstract void performEmptyAction(GameObject gameObj);
	
	public abstract Color getColor();
	
	public abstract String getName();
	
	public String toString() {
		return getName() + ": " + curValue + " / " + maxValue;
	}
	
}
