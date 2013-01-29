package Buildings;

import java.util.LinkedList;

import Abilities.BuildCollector;
import IO.SpriteLoader;
import Units.Collector;
import Units.Unit;

public class ManaAltar extends Building {
	
	public ManaAltar(int x, int y) {
		super(x, y, 100, SpriteLoader.loadAnimationFile("manaAltar"));
		productionUnits.add(new Collector(0, 0));
	}

	@Override
	public void setAbilities() {
		abilities.add(new BuildCollector());
	}

}
