package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Units.Collector;
import Units.Unit;

import Bars.Bar;
import Buildings.Building;
import Game.Main;
import GameObjects.ClickableObject;
import GameObjects.SelectableObject;

public class InformationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Main main;

	public InformationPanel(Main main, int controlPanelHeight) {
		super();
		this.main = main;
		this.setPreferredSize(new Dimension(100, controlPanelHeight));
		this.setBackground(Color.BLACK);
	}

	public void paint(Graphics g) {
		if(main.getSelection().size() == 1) {
			if(main.getSelection().getFirst() instanceof Unit) {
				Unit selectedUnit = (Unit)main.getSelection().getFirst();
				g.drawImage(selectedUnit.getFace(), 10, 10, null);
				g.setColor(Color.WHITE);
				int y = 0;
				for(Bar bar : selectedUnit.getBars()) {
					((Graphics2D)g).drawString(bar.toString(), 10, 120 + y);	
					y += 20;
				}
			} else {
				// TODO exception
				/*
				Building selectedBuilding = (Building)main.getSelection().getFirst();
				int dx = 0;
				for(Unit unit : selectedBuilding.getProductionQueue()) {
					g.setColor(Color.white);
					g.fillRect(10 + dx, 50, 64, 64);
					dx += 80;
				}
				*/
			}
		} else {
			int x = 10;
			for(ClickableObject selectable : main.getSelection()) {
				if(selectable instanceof Unit) {
					//Unit selectedUnit = (Unit)main.getSelection().getFirst();
					Unit selectedUnit = (Unit)selectable;
					g.drawImage(selectedUnit.getFace(), x, 10, null);
					g.setColor(Color.WHITE);
					int y = 0;
					for(Bar bar : selectedUnit.getBars()) {
						((Graphics2D)g).drawString(bar.toString(), x, 120 + y);
						y += 20;
					}
				}
				x += 128;
			}
			//System.out.println(main.getSelection().size());
		}
	}

}
