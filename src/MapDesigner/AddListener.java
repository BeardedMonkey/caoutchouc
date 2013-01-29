package MapDesigner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListener implements ActionListener {

	private CollisionMapDesigner owner;

	public AddListener(CollisionMapDesigner owner) {
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		owner.addPolygonToGraph(owner.getCurrentPolygon());
		owner.resetCurrentPolygon();
		owner.repaint();
	}

}
