package GUI;

import javax.swing.JButton;

import Abilities.Ability;

public class AbilityButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private Ability ability;
	
	public AbilityButton() {
		super();
	}
	
	public void setAbility(Ability ability) {
		this.setIcon(ability.getIcon());
		this.ability = ability;
	}
	
	public Ability getAbility() {
		return ability;
	}
	
}
