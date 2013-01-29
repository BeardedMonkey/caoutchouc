package MapDesigner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Geometry.Point;
import Geometry.Poly;
import Geometry.Segment;
import Geometry.VisibilityGraph;


public class CollisionMapDesigner extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	public static void main(String[] args) {
		JFrame gui = new JFrame("Collision Map Designer");
		gui.setLayout(new BorderLayout());
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
		CollisionMapDesigner mainPanel = new CollisionMapDesigner();
		gui.add(mainPanel);
		JButton addBut = new JButton("Add");
		addBut.addActionListener(new AddListener(mainPanel));
		gui.add(addBut, BorderLayout.EAST);
		JTextField textField = new JTextField();
		textField.addActionListener(new AddPointListener(mainPanel, textField));
		gui.add(textField, BorderLayout.SOUTH);
		gui.setVisible(true);
	}

	
	private int mx;
	private int my;
	private Poly curPolygon;
	private VisibilityGraph visGraph;

	public CollisionMapDesigner() {
		super(true);
		this.setOpaque(false);
		addMouseListener(this);
		addMouseMotionListener(this);
		curPolygon = new Poly();
		visGraph = new VisibilityGraph();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		addVertexToCurrent(new Point(mx, my));
		repaint();
		e.consume();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void paint(Graphics g) {
		g.setColor(Color.green);
		for(int i = 0; i < curPolygon.size(); i++) {
			Point P = curPolygon.get(i);
			Point Q = curPolygon.get(i + 1);
			g.drawOval(P.ix - 2, P.iy - 2, 4, 4);
			g.drawLine(P.ix, P.iy, Q.ix, Q.iy);
		}
		for(Poly polygon : visGraph.getPolygons()) {
			g.setColor(Color.red);
			g.fillPolygon(polygon.getPolygon());
			g.setColor(Color.blue);
			for(int i = 0; i < polygon.size(); i++) {
				Point P = polygon.get(i);
				Point Q = polygon.get(i + 1);
				g.drawOval(P.ix - 2, P.iy - 2, 4, 4);
				g.drawLine(P.ix, P.iy, Q.ix, Q.iy);
			}
		}
		g.setColor(Color.blue);
		for(Segment s : visGraph.getEdges()) {
			g.drawLine(s.P.ix, s.P.iy, s.Q.ix, s.Q.iy);
		}
	}

	public Poly getCurrentPolygon() {
		return curPolygon;
	}

	public void resetCurrentPolygon() {
		curPolygon = new Poly();
	}

	public void addPolygonToGraph(Poly currentPolygon) {
		visGraph.addPolygon(currentPolygon);
	}
	
	public void addVertexToCurrent(Point p) {
		curPolygon.addVertex(p);
		repaint();
	}

}
