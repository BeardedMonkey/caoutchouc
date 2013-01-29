package Bars;

import java.awt.Color;

import GameObjects.GameObject;

public class BuildBar extends Bar {

	public BuildBar() {
		super(100, 0);
		
	}

	@Override
	public Color getColor() {
		return Color.yellow;
	}

	@Override
	public String getName() {
		return "Remaining";
	}

	@Override
	public void performEmptyAction(GameObject gameObj) {
		// TODO Auto-generated method stub
		
	}

}
