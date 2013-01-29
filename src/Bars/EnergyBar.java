package Bars;

import Buildings.Building;
import GameObjects.GameObject;

import java.awt.Color;

import Units.Unit;

public class EnergyBar extends Bar {
	
	private final Color green = new Color(144, 238, 144);

	public EnergyBar(int maxValue) {
		super(maxValue);
	}
	
	public EnergyBar(int maxValue, int startValue) {
		super(maxValue, startValue);
	}

	@Override
	public void performEmptyAction(GameObject gameObj) {
		if(gameObj instanceof Unit) {
			((Unit)gameObj).kill();
		} else if(gameObj instanceof Building) {
			((Building)gameObj).destroy();
		}
	}
	
	public String getName() {
		return "HP";
	}

	@Override
	public Color getColor() {
		return green;
	}

}
