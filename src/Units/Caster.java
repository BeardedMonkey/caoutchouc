package Units;

import Bars.MagicBar;
import Game.Animation;

public abstract class Caster extends Unit {

	protected MagicBar magicBar;
	
	public Caster(int x, int y, Animation animation) {
		super(x, y, false, animation);
	}
	
	public MagicBar getMagicBar() {
		return magicBar;
	}

}
