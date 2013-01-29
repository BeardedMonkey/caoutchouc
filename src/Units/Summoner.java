package Units;

import Abilities.Bolt;
import Bars.EnergyBar;
import Bars.MagicBar;
import Buildings.ManaAltar;
import Game.Animation;
import GameObjects.ManaCrystal;
import Geometry.Rect;
import IO.SpriteLoader;

public class Summoner extends Caster {

	private static final Animation animation = SpriteLoader.loadAnimationFile("character");
	
	public Summoner(int x, int y) {
		super(x, y, animation);
		this.faceSprite = SpriteLoader.loadFace("face.png");
		this.width = animation.getWidth();
		this.height = animation.getHeight();
		this.rectangle = new Rect(width, height);
		this.energyBar = new EnergyBar(100, 100);
		this.magicBar = new MagicBar(100, 100);
		bars.add(magicBar);
		bars.add(energyBar);
	}

	@Override
	protected void setAbilities() {
		abilities.add(new Bolt());
	}
	
	public MagicBar getMagicBar() {
		return magicBar;
	}

	@Override
	public void goToCrystal(ManaCrystal manaCrystal, ManaAltar closestManaAltar) {
		
	}

}
