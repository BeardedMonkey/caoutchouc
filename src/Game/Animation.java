package Game;

import java.awt.image.BufferedImage;
import java.util.TreeMap;

public class Animation {

	private int width;
	private int height;
	private TreeMap<State, Tuple<BufferedImage[], Integer>> spriteMap;
	
	public Animation(int width, int height, TreeMap<State, Tuple<BufferedImage[], Integer>> spriteMap) {
		this.spriteMap  = spriteMap;
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Tuple<BufferedImage[], Integer> getStateSprites(State state) {
		return spriteMap.get(state);
	}
	
}
