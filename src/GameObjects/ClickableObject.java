package GameObjects;

import java.util.LinkedList;

import Abilities.Ability;
import Bars.Bar;
import Game.Animation;

public abstract class ClickableObject extends AnimatedObject {

	protected boolean isSelected;
	protected LinkedList<Bar> bars;
	protected LinkedList<Ability> abilities;

	
	public ClickableObject(int x, int y, int width, int height, Animation animation) {
		super(x, y, width, height, animation);
		bars = new LinkedList<Bar>();
		abilities = new LinkedList<Ability>();
	}
	
	public ClickableObject(int x, int y, Animation animation) {
		super(x, y, animation);
		bars = new LinkedList<Bar>();
		abilities = new LinkedList<Ability>();
	}

	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean selected) {
		this.isSelected = selected;
	}
	
	public LinkedList<Ability> getAbilities() {
		return abilities;
	}
	
	public LinkedList<Bar> getBars() {
		return bars;
	}
	
}
