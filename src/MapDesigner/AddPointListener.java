package MapDesigner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import Geometry.Point;

public class AddPointListener implements ActionListener {

	private CollisionMapDesigner owner;
	private JTextField textField;
	
	public AddPointListener(CollisionMapDesigner owner, JTextField textField) {
		this.owner = owner;
		this.textField = textField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		owner.addVertexToCurrent(new Point(textField.getText()));
		textField.setText("");
	}

}
