package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MainFrame extends JFrame implements KeyListener, ActionListener {

	private final Timer timer = new Timer(10, this);
	
	public MainFrame(String title) {
		super(title);
		timer.start();
	}
	
	public boolean isFocusable() {
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("BLA");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("BLA");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("BLA");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
}
