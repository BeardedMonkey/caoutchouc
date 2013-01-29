package Tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SliceMaker {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String filename = "fairyface";
		BufferedImage img = ImageIO.read(new File(filename + ".png"));
		int height = img.getHeight();
		int width = img.getWidth();
		int k = 1;
		int horz = 2;
		int vert = 4;
		int hSize = height / horz;
		int wSize = width / vert;
		for(int y = 0; y < height; y += hSize) {
			for(int x = 0; x < width; x += wSize) {
				BufferedImage tile = img.getSubimage(x, y, wSize, hSize);
				ImageIO.write(tile, "png", new File(filename + k + ".png"));
				k++;
			}
		}
	}

}
