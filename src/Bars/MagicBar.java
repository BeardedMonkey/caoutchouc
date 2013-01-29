package Bars;

import Buildings.Building;
import GameObjects.GameObject;

import java.awt.Color;

import Units.Unit;

public class MagicBar extends Bar {
	
	private final Color blue = new Color(30, 144, 255);


	public MagicBar(int maxValue) {
		super(maxValue);
	}
	
	public MagicBar(int maxValue, int startValue) {
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
		return "MP";
	}

	@Override
	public Color getColor() {
		return blue;
	}

}
