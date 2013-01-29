package Tests;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import IO.SpriteLoader;
import Utils.Images;

public class ImageBorder {

	public static void main(String[] args) {
		BufferedImage img = SpriteLoader.loadSprite("front1.png");
		
		int colour = img.getRGB(img.getWidth() / 2, img.getHeight() / 2);
		int r = (colour)&0xFF;
		int g = (colour>>8)&0xFF;
		int b = (colour>>16)&0xFF;
		int a = (colour>>24)&0xFF;
		System.out.println(r + " " + g + " " + b + " " + a);
		
		LinkedList<int[]> borderPositions = Images.getBorder(img, 1);
	
		for(int[] pos : borderPositions) {
			//int colour = img.getRGB(pos[0], pos[1]);
			//System.out.println((colour>>24) & 0xff);
			//System.out.println((255 << 24));
			img.setRGB(pos[0], pos[1], -1689547);
			
			

		}
		
		System.out.println(Integer.toBinaryString(-1689547));
		System.out.println(Integer.parseInt("1111111111001100011100000110101", 2));
		System.out.println(-Integer.parseInt("1111111111001100011100000110101", 2));
		System.out.println(0xFF);
		
		try {
			ImageIO.write(img, "png", new File("test.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
