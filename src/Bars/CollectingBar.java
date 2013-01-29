package Bars;

import java.awt.Color;

import GameObjects.GameObject;

public class CollectingBar extends Bar {

	public CollectingBar(long maxValue) {
		super(maxValue, 0);
	}

	@Override
	public void performEmptyAction(GameObject gameObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		return Color.pink;
	}

	public String getName() {
		return "MN";
	}
}
