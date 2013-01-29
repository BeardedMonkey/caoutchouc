package Units;

import Abilities.Collect;
import Actions.GatherMana;
import Actions.GiveMana;
import Actions.Move;
import Bars.CollectingBar;
import Bars.EnergyBar;
import Buildings.Building;
import Buildings.ManaAltar;
import Game.Animation;
import GameObjects.ManaCrystal;
import Geometry.Geometry;
import Geometry.Point;
import Geometry.Rect;
import IO.SpriteLoader;

public class Collector extends Unit {

	private static final Animation animation = SpriteLoader.loadAnimationFile("fairy");
	private final long collectTime = 4 * (long)10e8;
	
	private CollectingBar collectBar;
	
	public Collector(int x, int y) {
		super(x, y, true, animation);
		this.faceSprite = SpriteLoader.loadFace("fairy.png");
		this.width = animation.getWidth();
		this.height = animation.getHeight();
		this.rectangle = new Rect(width, height);
		this.energyBar = new EnergyBar(100, 100);
		this.collectBar = new CollectingBar(10);
		bars.add(collectBar);
		bars.add(energyBar);
	}

	@Override
	protected void setAbilities() {
		this.abilities.add(new Collect());
	}
	
	public void setMana(double percentage) {
		collectBar.setPercentage(percentage);
	}
	
	public CollectingBar getCollectingBar() {
		return collectBar;
	}
	
	public long getRemainingTime() {
		return (long)(collectTime * (1 - collectBar.getPercentage()));
	}

	@Override
	public void goToCrystal(ManaCrystal manaCrystal, ManaAltar closestManaAltar) {
		setPath(manaCrystal.getPoint());
		addAction(new GatherMana(this, manaCrystal, getRemainingTime()));
		if(closestManaAltar != null) {
			// TODO we need to check again at each step if that the a mana altar exists
			addAction(new Move(this, closestManaAltar.getPoint()));
			addAction(new GiveMana(this));
			getActionList().setLoop(true);
		}
	}
	
}
