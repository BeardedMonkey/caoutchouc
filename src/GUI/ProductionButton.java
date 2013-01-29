package GUI;

import javax.swing.JButton;

import Units.Unit;

import Actions.Action;

public class ProductionButton extends JButton {

	private static final long serialVersionUID = 1L;

	private Unit unit;

	public ProductionButton(Unit unit) {
		super();
		this.unit = unit;
	}

	public Unit getUnitCopy() {
		return unit.getCopy();
	}


}
