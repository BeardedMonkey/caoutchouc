package Bullets;

import Game.Animation;
import GameObjects.GameObject;
import GameObjects.MovingObject;
import Geometry.Vector;
import IO.SpriteLoader;

public class ShockBall extends MovingObject {

	public ShockBall(int x, int y, Vector v) {
		super(x, y, SpriteLoader.loadAnimationFile("bolt"), v, 1, true, -1);
		
	}

	@Override
	public void colide(GameObject go) {
		// TODO Auto-generated method stub
		
	}

}
