package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.LinkedList;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JPanel;

import Units.Unit;

import Abilities.Ability;
import Buildings.Building;
import Game.Main;
import Game.SelectionManager;
import GameObjects.ClickableObject;

public class ButtonPanel extends JPanel {


	private static final long serialVersionUID = 1L;

	private final int size = 4;

	private int cur;

	private JButton[] buttons;

	public ButtonPanel(int controlPanelHeight, ControlPanel controlPanel) {
		super();
		this.setPreferredSize(new Dimension(200, controlPanelHeight));
		this.setBackground(Color.blue);
		GridLayout layout = new GridLayout(size, size);
		layout.setHgap(10);
		layout.setVgap(10);
		setLayout(layout);
		buttons = new JButton[size * size];
		for(int i = 0; i < size * size; i++) {
			buttons[i] = new JButton();
			buttons[i].setVisible(false);
			add(buttons[i]);
		}
		cur = 0;
	}

	private void addButton(JButton button) {
		buttons[cur] = button;
		buttons[cur].setVisible(true);
		cur++;
	}

	private boolean removeButton() {
		if(cur > 0) {
			buttons[cur - 1] = new JButton();
			buttons[cur - 1].setVisible(false);
			cur--;
			return true;
		}
		return false;
	}
	
	private void clearAllButtons() {
		while(removeButton());
	}

	public void setButtonsWRTSelection(SelectionManager selectionManager) {
		if(selectionManager.isBuilding()) {
			Building selectedBuilding = (Building)selectionManager.getFirst();
			for(Unit unit : selectedBuilding.getProductionUnits()) {
				System.out.println("update!");
				addButton(new ProductionButton(unit));
			}
		} else {
			
		}
		repaint();
	}

}
