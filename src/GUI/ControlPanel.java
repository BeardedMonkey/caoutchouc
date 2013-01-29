package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Abilities.Ability;
import Game.Main;
import Game.SelectionManager;
import GameObjects.ClickableObject;
import GameObjects.SelectableObject;

public class ControlPanel extends JPanel {
	
	private final int height = 200;
	private Main main;
	
	private ButtonPanel abilityPanel;
	private ButtonPanel bPanel;
	
	public ControlPanel(Main main) {
		super();
		this.main = main;
		this.setPreferredSize(new Dimension(0, 200));
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.bPanel = new ButtonPanel(height, this);
		this.add(bPanel, BorderLayout.EAST);
		this.add(new InformationPanel(main, height), BorderLayout.CENTER);
		this.add(new MiniMapPanel(main, height), BorderLayout.WEST);
	}

	public void updateAbilityButtons(LinkedList<ClickableObject> selection) {
		
		//abilityPanel.updateButtons(selection);
	}
	
	public void clearAbilityButtons() {
		//abilityPanel.clearButtons();
	}
	
	public AbilityPanel getAbilityPanel() {
		//return abilityPanel;
		return null;
	}
	
	public Main getMain() {
		//return main;
		return null;
	}
	
	public boolean hasAbilitySelected() {
		//return abilityPanel.hasSelectedAbility();
		return false;
	}
	
	public Ability getSelectedAbility() {
		//return abilityPanel.getSelectedAbility();
		return null;
	}

	public void clearSelectedAbility() {
		//abilityPanel.deselectAbility();
	}
	
	public void updateButtonPanel(SelectionManager selectionManager) {
		bPanel.setButtonsWRTSelection(selectionManager);
	}
	
}
