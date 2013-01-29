package Abilities;

import IO.SpriteLoader;

public class Collect extends Ability {

	public Collect() {
		super("Collect", SpriteLoader.loadIcon("collect.png"), 20, 50, 0, 0, (int)10e8, SpriteLoader.loadAnimationFile("bolt"));
	}

}
