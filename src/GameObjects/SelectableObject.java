package GameObjects;

import Game.Animation;

public class SelectableObject extends ClickableObject {

	public SelectableObject(int x, int y, int width, int height, Animation animation) {
		super(x, y, width, height, animation);
	}

	public SelectableObject(int x, int y, Animation animation) {
		super(x, y, animation);
	}

	@Override
	public void setObjectBehind(GameObject object) {
		// TODO Auto-generated method stub
		
	}

}
