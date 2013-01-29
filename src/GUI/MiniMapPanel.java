package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Units.Unit;
import Utils.Util;

import Game.Main;

public class MiniMapPanel extends JPanel {

	private Main main;
	private int width, height;
	private int miniMapWidth, miniMapHeight;
	private int miniMapOrigX, miniMapOrigY;
	
	public MiniMapPanel(Main main, int controlPanelHeight) {
		super();
		this.main = main;
		width = controlPanelHeight;
		height = controlPanelHeight;
		this.setPreferredSize(new Dimension(width, height));
		// Compute the mini-map size
		int mapWidth = main.getMap().getTileWidth();
		int mapHeight = main.getMap().getTileHeight();
		int gcd = Util.gcd(mapWidth, mapHeight);
		mapWidth /= gcd;
		mapHeight /= gcd;
		int k = Math.min(192 / mapWidth, 192 / mapHeight);
		miniMapWidth = mapWidth * k;
		miniMapHeight = mapHeight * k;
		// Compute the mini-map origin
		miniMapOrigX = (width - miniMapWidth) / 2;
		miniMapOrigY = (height - miniMapHeight) / 2;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(miniMapOrigX, miniMapOrigY, miniMapWidth, miniMapHeight);
		for(Unit unit : main.getUnits()) {
			g.setColor(Color.RED);
			int xInMinimap = unit.getIntX() * miniMapWidth / main.getMap().getWidth()  + miniMapOrigX;
			int yInMinimap = unit.getIntY() * miniMapHeight / main.getMap().geHeight() + miniMapOrigY;
			g.fillRect(xInMinimap - 1, yInMinimap - 1, 3, 3);
		}
	}
	
}
