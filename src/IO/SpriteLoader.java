package IO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Game.Animation;
import Game.State;
import Game.Tuple;

public class SpriteLoader {

	public static Animation loadAnimationFile(String animationFilename) {
		try {
			Scanner reader = new Scanner(new FileReader("Resources/Animations/" + animationFilename));
			String spritesPath = reader.next().substring(1);
			int width = reader.nextInt();
			int height = reader.nextInt();
			int nbOfSupportedStates = reader.nextInt();
			TreeMap<State, Tuple<BufferedImage[], Integer>> spriteMap = new TreeMap<State, Tuple<BufferedImage[], Integer>>();
			for(int i = 0; i < nbOfSupportedStates; i++) {
				String stateStr = reader.next();
				int nbOfSprites = reader.nextInt();
				int animationSpeed = reader.nextInt();
				BufferedImage[] sprites = new BufferedImage[nbOfSprites];
				for(int j = 0; j < nbOfSprites; j++) {
					sprites[j] = ImageIO.read(new File("Resources/Sprites/" + spritesPath + reader.next()));
				}
				spriteMap.put(State.getFromStr(stateStr), new Tuple<BufferedImage[], Integer>(sprites, animationSpeed));
			}
			Tuple<BufferedImage[], Integer> tuple = new Tuple<BufferedImage[], Integer>(new BufferedImage[] {loadSprite("empty.png")}, 1);
			spriteMap.put(State.DEAD, tuple);
			return new Animation(width, height, spriteMap);
		} catch (FileNotFoundException e) {
			System.out.println("SpriteAnimationLoader: file <" + animationFilename + "> was not found in the folder <Animations>");
		} catch (IOException e) {
			System.out.println("SpriteAnimationLoader: file not found");
		}
		return null;
	}
	
	public static BufferedImage loadSprite(String spriteFilename) {
		try {
			return ImageIO.read(new File("Resources/Sprites/" + spriteFilename));
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage loadFace(String spriteFilename) {
		try {
			return ImageIO.read(new File("Resources/Faces/" + spriteFilename));
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		return null;
	}

	public static ImageIcon loadIcon(String spriteFilename) {
		try {
			return new ImageIcon(ImageIO.read(new File("Resources/Icons/" + spriteFilename)));
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		return null;
	}

}
