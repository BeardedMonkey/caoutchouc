package Utils;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Images {

	/**
	 * Computes the border of an images together with the distances.
	 * @param sprite
	 * @param r
	 * @return list of [x, y, distance], 1 <= distance <= r
	 */
	public static LinkedList<int[]> getBorder(BufferedImage sprite, int r) {
		LinkedList<int[]> borderPositions = new LinkedList<int[]>();
		for(int i = r; i < sprite.getHeight() - r; i++) {
			for(int j = r; j < sprite.getWidth() - r; j++) {
				boolean found = false;
				for(int k = 1; !found && k <= r; k++) {
					if(sprite.getRGB(j, i) == 0 && (sprite.getRGB(j - k, i - k) != 0 || sprite.getRGB(j + k, i - k) != 0 || sprite.getRGB(j - k, i + k) != 0 || sprite.getRGB(j + k, i + k) != 0 || sprite.getRGB(j - k, i) != 0 || sprite.getRGB(j + k, i) != 0 || sprite.getRGB(j, i + k) != 0 || sprite.getRGB(j, i - k) != 0 )) {
						borderPositions.add(new int[] {j, i, k});
						found = true;
					}
				}			
			}
		}
		return borderPositions;
	}
	
}
