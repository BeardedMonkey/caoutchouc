package Abilities;

import IO.SpriteLoader;

public class BuildCollector extends Ability {

	public BuildCollector() {
		super(
				"Build fairy",
				SpriteLoader.loadIcon("build_fairy.png"), 
				0, 
				0, 
				0, 
				0, 
				0, 
				null
		);
	}

}
