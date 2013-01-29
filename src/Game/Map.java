package Game;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import IO.MapLoader;

public class Map {

	private LinkedList<BufferedImage>[][] mapTilesGroundLayer;
	private LinkedList<BufferedImage>[][] mapTilesSkyLayer;
	private int width;
	private int height;
	private boolean[][] passability; 
	
	@SuppressWarnings("unchecked")
	public Map(String mapFilename) {
		Tuple<LinkedList<BufferedImage>[][][], boolean[][]> mapData = MapLoader.loadMapFile(mapFilename);
		mapTilesGroundLayer = mapData.getX()[0];
		mapTilesSkyLayer = mapData.getX()[1];
		width = mapTilesGroundLayer[0].length;
		height = mapTilesGroundLayer.length;
		passability = mapData.getY();
	}
	
	public int getTileWidth() {
		return width;
	}
	
	public int getTileHeight() {
		return height;
	}
	
	public int getWidth() {
		return width * getTileSize();
	}
	
	public int geHeight() {
		return height * getTileSize();
	}
	
	public int getTileSize() {
		return 32; // TODO: Use the size defined in the tileset
	}
	
	public LinkedList<BufferedImage> getGroundLayerGraphicsAt(int i, int j) {
		return mapTilesGroundLayer[i][j];
	}
	
	public LinkedList<BufferedImage> getSkyLayerGraphicsAt(int i, int j) {
		return mapTilesSkyLayer[i][j];
	}
}
