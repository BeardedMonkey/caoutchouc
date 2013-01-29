package Abilities;

import IO.SpriteLoader;

public class Bolt extends Ability {

	public Bolt() {
		super("Bolt", SpriteLoader.loadIcon("bolt.png"), 20, 50, 100, 20, 1, SpriteLoader.loadAnimationFile("bolt"));
	}

}
