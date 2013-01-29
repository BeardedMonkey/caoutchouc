package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JPanel;

import Abilities.Ability;
import Buildings.Building;
import GameObjects.ClickableObject;
import GameObjects.SelectableObject;
import Units.Collector;
import Units.Unit;

public class ProductionPanel extends JPanel {


	private static final long serialVersionUID = 1L;

	private final int size = 4;

	private int cur;
	private ControlPanel owner;
	private Building building;
	
	private Ability selectedAbility;

	private ProductionButton[] buttons;

	public ProductionPanel(int controlPanelHeight, ControlPanel controlPanel, Building building) {
		super();
		this.owner = controlPanel;
		this.setPreferredSize(new Dimension(200, controlPanelHeight));
		this.setBackground(Color.RED);
		GridLayout layout = new GridLayout(size, size);
		layout.setHgap(10);
		layout.setVgap(10);
		setLayout(layout);
		selectedAbility = null;
		this.building = building;
		buttons = new ProductionButton[size * size];
		for(int i = 0; i < size * size; i++) {
			if(i < building.getProductionUnits().size()) {
				buttons[i] = new ProductionButton(building.getProductionUnits().get(i));
				buttons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//owner.getMain().getUnits().add(((ProductionButton)e.getSource()).getUnitCopy());
						owner.getMain().getUnits().add(new Collector(200, 200));
					}
				});
				buttons[i].setVisible(true);
			} else {
				buttons[i] = new ProductionButton(null);
				buttons[i].setVisible(false);				
			}
			this.add(buttons[i]);
		}
		cur = 0;
	}

	public boolean hasSelectedAbility() {
		return this.selectedAbility != null;
	}

	public Ability getSelectedAbility() {
		return selectedAbility;
	}

	public void deselectAbility() {
		selectedAbility = null;
	}

	public void addAbility(Ability ability) {
		
	}

	public void updateButtons(LinkedList<ClickableObject> selection) {
		for(ClickableObject selectable : selection) {
			for(Ability ability : selectable.getAbilities()) {
				addAbility(ability);
			}
		}
	}

	public void clearButtons() {
		while(cur > 0) {
			buttons[cur].setVisible(false);
			cur--;
		}
		buttons[0].setVisible(false);
	}

}
