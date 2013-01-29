package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.TreeSet;

import javax.swing.JPanel;

import Abilities.Ability;
import GameObjects.ClickableObject;
import GameObjects.SelectableObject;
import Units.Unit;

public class AbilityPanel extends JPanel {


	private static final long serialVersionUID = 1L;

	private final int size = 4;

	private int cur;
	private GridLayout layout;
	private ControlPanel owner;
	private TreeSet<String> abilitiesListed;
	private Ability selectedAbility;

	private AbilityButton[] buttons;

	public AbilityPanel(int controlPanelHeight, ControlPanel controlPanel) {
		super();
		this.owner = controlPanel;
		this.setPreferredSize(new Dimension(200, controlPanelHeight));
		this.setBackground(Color.RED);
		this.layout = new GridLayout(size, size);
		this.layout.setHgap(10);
		this.layout.setVgap(10);
		this.setLayout(layout);
		this.abilitiesListed = new TreeSet<String>();
		this.selectedAbility = null;
		buttons = new AbilityButton[size * size];
		for(int i = 0; i < size * size; i++) {
			AbilityButton but = new AbilityButton();
			but.setVisible(false);
			buttons[i] = but;
			this.add(but);
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

	public void addHability(Ability ability) {
		if(!this.abilitiesListed.contains(ability.getName())) {
			buttons[cur].setAbility(ability);
			buttons[cur].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedAbility = ((AbilityButton)e.getSource()).getAbility();
					owner.getMain().getGamePanel().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				}
			});
			this.abilitiesListed.add(ability.getName());
			repaint();
			buttons[cur].setVisible(true);
			cur++;
		}
	}

	public void updateButtons(LinkedList<ClickableObject> selection) {
		for(ClickableObject selectable : selection) {
			for(Ability ability : selectable.getAbilities()) {
				addHability(ability);
			}
		}
	}

	public void clearButtons() {
		this.abilitiesListed.clear();
		while(cur > 0) {
			buttons[cur].setVisible(false);
			cur--;
		}
		buttons[0].setVisible(false);
	}

}
