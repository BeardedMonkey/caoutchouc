package IO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import Game.Tuple;

public class MapLoader {

	@SuppressWarnings("unchecked")
	public static Tuple<LinkedList<BufferedImage>[][][], boolean[][]> loadMapFile(String mapFilename) {
		try {
			Scanner reader = new Scanner(new FileReader("Resources/Maps/" + mapFilename));
			String tilesetFilename = reader.next();
			TreeMap<String, Tuple<BufferedImage, Integer>> tileMap = loadTilesetFile(tilesetFilename);
			int tileSize = reader.nextInt(); // Not used for now, assumed to be 32
			int height = reader.nextInt();
			int width = reader.nextInt();
			LinkedList<BufferedImage>[][] mapGoundTiles = new LinkedList[height][width];
			boolean[][] passability = new boolean[height][width];
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					mapGoundTiles[i][j] = new LinkedList<BufferedImage>();
					String tileIdsStr = reader.next();
					tileIdsStr = tileIdsStr.substring(1, tileIdsStr.length() - 1);
					String[] tileIds = tileIdsStr.split(",");
					passability[i][j] = true;
					for(int k = 0; k < tileIds.length; k++) {
						Tuple<BufferedImage, Integer> tileData = tileMap.get(tileIds[k]);
						mapGoundTiles[i][j].add(tileData.getX());
						if(k == 0 && tileData.getY() == 1) { // The passability is given by the first tile
							passability[i][j] = false;
						}
					}
				}
			}
			// No passability for sky tiles
			LinkedList<BufferedImage>[][] mapSkyTiles = new LinkedList[height][width];
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					mapSkyTiles[i][j] = new LinkedList<BufferedImage>();
					String tileIdsStr = reader.next();
					if(!tileIdsStr.equals("[]")) {
						tileIdsStr = tileIdsStr.substring(1, tileIdsStr.length() - 1);
						String[] tileIds = tileIdsStr.split(",");
						for(String id : tileIds) {
							mapSkyTiles[i][j].add(tileMap.get(id).getX());
						}
					}
				}
			}
			LinkedList<BufferedImage>[][][] layers = new LinkedList[2][][];
			layers[0] = mapGoundTiles;
			layers[1] = mapSkyTiles;
			return new Tuple(layers, passability);
		} catch (FileNotFoundException e) {
			System.out.println("MapLoader: file <" + mapFilename + "> was not found in the folder <Maps>");
		}
		return null;
	}


	private static TreeMap<String, Tuple<BufferedImage, Integer>> loadTilesetFile(String tilesetFilename) {
		try {
			Scanner reader = new Scanner(new FileReader("Resources/Tilesets/" + tilesetFilename));
			TreeMap<String, Tuple<BufferedImage, Integer>> tileMap = new TreeMap<String, Tuple<BufferedImage, Integer>>();
			while(reader.hasNext()) {
				String id = reader.next();
				String tileFilename = reader.next();
				int passability = reader.nextInt(); // 1 means obstacle
				try {
					tileMap.put(id, new Tuple(ImageIO.read(new File("Resources/Tiles/" + tileFilename)), passability));
				} catch (IOException e) {
					System.out.println("MapLoader: file <" + tileFilename + "> was not found in the folder <Tiles>");
					e.printStackTrace();
				}
			}
			return tileMap;
		} catch (FileNotFoundException e) {
			System.out.println("MapLoader: file <" + tilesetFilename + "> was not found in the folder <Tilesets>");
		}
		return null;
	}


}
