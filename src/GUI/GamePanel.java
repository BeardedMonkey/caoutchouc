package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.LinkedList;

import javax.swing.JPanel;

import Units.Caster;
import Units.Collector;
import Units.Unit;
import Actions.GatherMana;
import Actions.GiveMana;
import Actions.Look;
import Actions.Move;
import Actions.PerformAnimation;
import Actions.SetLoop;
import Actions.Wait;
import Bars.Bar;
import Buildings.*;
import Game.Main;
import Game.PathSegment;
import GameObjects.AnimatedObject;
import GameObjects.ClickableObject;
import GameObjects.GameObject;
import GameObjects.ManaCrystal;
import GameObjects.MovingObject;
import GameObjects.*;
import Geometry.Geometry;
import Geometry.*;
import Bullets.*;
import IO.SpriteLoader;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener  {

	private static final long serialVersionUID = 1L;

	private static final int[][] adjacent = new int[][] {
		{1, 0},
		{-1, 0},
		{0, 1},
		{0, -1},
		{1, 1},
		{-1, -1},
		{1, -1},
		{-1, 1}
	};

	private int mx, my;
	private boolean isButtonPressed;
	private int selectStartX, selectStartY;
	private LinkedList<Unit> flyingUnits;
	private float dashPhase = 0f;

	boolean drawBackground;
	int DX = 0;
	int DY = 0;

	private Main main;


	public GamePanel(Main main) {
		super(true);
		this.main = main;
		this.isButtonPressed = false;
		this.setOpaque(false);
		setBackground( Color.gray );
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.black);

		addKeyListener(this);
		drawBackground = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		if(e.getButton() == MouseEvent.BUTTON1) { // Left button clicked 
			main.movingObjects.add(new ShockBall(100, 100, new Vector(1, 1)));
			/*
			if(main.hasAbilitySelected()) { // An ability is selected, perform action
				Caster caster = main.getCorrespondingCaster(main.getSelectedAbility());
				caster.addAction(new Look(caster, caster.getPoint()));
				caster.addAction(new Wait(caster, main.getSelectedAbility().getCastTime()));
				caster.addAction(new PerformAnimation(caster, main, main.getSelectedAbility().getAnimation(), mx, my));
				this.setCursor(Cursor.getDefaultCursor());
			} else {
				ClickableObject selected = null;
				// Check if some unit is under the mouse
				for(Unit unit : main.getUnits()) {
					if(Geometry.isInRectangle(mx, my, unit.getIntX(), unit.getIntY(), unit.getRect())) {
						selected = unit;
						break;
					}
				}
				// Check if some other clickable object is under the mouse
				if(selected == null) {
					for(ClickableObject object : main.getClickableObjects()) {
						if(Geometry.isInRectangle(mx, my, object.getIntX(), object.getIntY(), object.getRectangle())) {
							selected = object;
						}
					}
				}
				main.setSelection(selected);	
			}			
			*/
		} else if(e.getButton() == MouseEvent. BUTTON2) { // Middle button clicked

		} else { // Right button clicked
			ClickableObject object = main.getObjectAt(new Point(mx, my));
			if(object == null) {
				if(main.getSelection().size() == 1 && main.getSelection().getFirst() instanceof Building) {
					((Building)main.getSelection().getFirst()).setRallyPoint(new Point(mx, my));
				} else {
					// No object found so just move
					LinkedList<Unit> selection = new LinkedList<Unit>();
					for(Unit unit : main.getUnits()) {
						if(unit.isSelected()) {
							selection.add(unit);
						}
					}
					if(!selection.isEmpty()) {
						selection.removeFirst().setPath(new Point(mx, my));
						int i = 0;
						int m = 0;
						for(Unit unit : selection) {
							unit.setPath(new Point(mx + (m + 1) * adjacent[i][0] * 32, my + (m + 1) * adjacent[i][1] * 32));
							i = (i + 1) % adjacent.length;
							m = (m + 1) / adjacent.length;
						}
					}
				}
			} else {
				LinkedList<Collector> collectors = new LinkedList<Collector>();
				for(ClickableObject selected : main.getSelection()) {
					if(selected instanceof Collector) {
						collectors.add((Collector)selected);
					}
				}
				if(object instanceof ManaCrystal) {
					for(Unit unit : main.getUnits()) {
						if(unit.isSelected()) {
							unit.goToCrystal((ManaCrystal)object, main.getClosestManaAltar(object.getPoint()));
						}
					}

					/*
					for(Collector collector : collectors) {
						//if(collector.isSelected()) {
						Point closestManaAltarPos = null;
						double distance = Double.POSITIVE_INFINITY;
						Point crystalPos = new Point(object.getIntX(), object.getIntY());
						for(Building building : main.getBuildings()) {
							if(building instanceof ManaAltar) {
								Point manaAltarPos = new Point(building.getIntX(), building.getIntY());
								if(distance > Geometry.squareDistance(manaAltarPos, crystalPos)) {
									distance = Geometry.squareDistance(manaAltarPos, crystalPos);
									closestManaAltarPos = manaAltarPos;
								}
							}
						}
						if(closestManaAltarPos == null) {
							collector.setPath(crystalPos);
							collector.addAction(new GatherMana(collector, (ManaCrystal)object, collector.getRemainingTime()));
						} else {
							// TODO we need to check again that the a mana altar exists
							collector.setPath(crystalPos);
							collector.addAction(new GatherMana(collector, (ManaCrystal)object, collector.getRemainingTime()));
							collector.addAction(new Move(collector, closestManaAltarPos));
							collector.addAction(new GiveMana(collector));
							collector.getActionList().setLoop(true);
						}
						//}
					 * 

					}
					 * */
				} else if(object instanceof ManaAltar) {
					for(Collector collector : collectors) {
						collector.getActionList().clear();
						if(!collector.getCollectingBar().isEmpty()) {
							collector.addAction(new Move(collector, new Point(object.getIntX(), object.getIntY())));
							collector.addAction(new GiveMana(collector));	
						}
						ManaCrystal closestManaCrystal = main.getClosestCrystal(collector.getPoint());
						if(closestManaCrystal != null) {
							collector.addAction(new SetLoop(collector, true));
							collector.addAction(new Move(collector, closestManaCrystal.getPoint()));
							collector.addAction(new GatherMana(collector, closestManaCrystal, collector.getRemainingTime()));
							collector.addAction(new Move(collector, object.getPoint()));
							collector.addAction(new GiveMana(collector));
						}
					}
				}
			}
		}

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
		mx = e.getX();
		my = e.getY();
		if(e.getButton() == MouseEvent.BUTTON1) { // Left button pressed
			if(!main.hasAbilitySelected()) {
				isButtonPressed = true;
				selectStartX = mx;
				selectStartY = my;
				setBackground(Color.gray);
			}
		} else if(e.getButton() == MouseEvent.BUTTON2) { // Middle button pressed
			//System.out.println("middle button pressed");
		} else {  // Right button pressed
			//System.out.println("right button pressed");

		}
		repaint();
		e.consume();
	}

	private PathSegment computePathSegment(int x, int y, int mx, int my) {
		double dx = mx - x;
		double dy = my - y;
		double norm = Math.sqrt(dx * dx + dy * dy);
		double speed = 1.5;
		Point destination = new Point(mx, my);
		Point direction = new Point((speed * dx) / norm, (speed * dy) / norm);
		return new PathSegment(direction, destination);
	}


	private LinkedList<PathSegment> computePath(int x, int y, int mx, int my) {
		LinkedList<PathSegment> p = new LinkedList<PathSegment>();
		double dx = mx - x;
		double dy = my - y;
		double norm = Math.sqrt(dx * dx + dy * dy);
		double speed = 1.5;
		Point destination = new Point(mx, my);
		Point direction = new Point((speed * dx) / norm, (speed * dy) / norm);
		p.add(new PathSegment(direction, destination));
		return p;
	}

	@Override
	public void mouseReleased(MouseEvent e) {  // Called after a button is released
		mx = e.getX();
		my = e.getY();
		if(!main.hasAbilitySelected()) {
			if(e.getButton() == MouseEvent.BUTTON1) { // Left button released
				LinkedList<ClickableObject> selection = new LinkedList<ClickableObject>();
				int x = Math.min(mx, selectStartX);
				int y = Math.min(my, selectStartY);
				int width = Math.abs(selectStartX - mx);
				int height =  Math.abs(selectStartY - my);
				for(Unit unit : main.getUnits()) {
					if(unit.isInRectangle(x, y, width, height)) {
						selection.add(unit);
						unit.setSelected(true);
					} else {
						unit.setSelected(false);
					}
				}
				isButtonPressed = false;
				main.setSelection(selection);
			} else if(e.getButton() == MouseEvent.BUTTON2) { // Middle button released

			} else { // Right button released

			}
		} else {
			//main.addAnimatedObject(new AnimatedObject(mx, my, main.getSelectedAbility().getAnimation(), true, State.PERFORMING_ACTION));
			//main.clearSelectedAbility();
			//this.setCursor(Cursor.getDefaultCursor());
		}
		repaint();
		e.consume();
	}

	public void mouseMoved(MouseEvent e) {  // called during motion when no buttons are down
		mx = e.getX();
		my = e.getY();
		repaint();
		e.consume();
	}

	public void mouseDragged(MouseEvent e) {  // called during motion with buttons down
		mx = e.getX();
		my = e.getY();
		repaint();
		e.consume();
	}

	public void paint(Graphics g) {
		
		
		// Draw the groung map
		if(drawBackground) {
			for(int i = 0; i < main.getMap().getTileHeight(); i++) {
				for(int j = 0; j < main.getMap().getTileWidth(); j++) {
					for(BufferedImage tile : main.getMap().getGroundLayerGraphicsAt(i, j)) {
						g.drawImage(tile, j * main.getMap().getTileSize() + DY, i * main.getMap().getTileSize() + DX, null);
					}
				}
			}
		}
		//drawBackground = false;

		for(Building building : main.getBuildings()) {
			BufferedImage sprite = building.getSprite();
			int xDraw = (int)(building.getX() - sprite.getWidth() / 2);
			int yDraw = (int)(building.getY() - sprite.getHeight() / 2);
			g.drawImage(sprite, xDraw, yDraw, null);

			if(building.isSelected()) {
				int dy = -7;
				for(Bar bar : building.getBars()) {
					// Draw energy bar background
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(xDraw, yDraw + dy, building.getWidth(), 5);
					// Draw energy bar content
					g.setColor(bar.getColor());
					g.fillRect(xDraw, yDraw + dy, (int)(bar.getPercentage() * building.getWidth()), 5);
					// Draw energy bar contour
					g.setColor(Color.black);
					g.drawRect(xDraw, yDraw + dy, building.getWidth(), 5);
					dy -= 7;
				}

				// Draw rally point
				if(building.getRallyPoint() != null) {
					
					Graphics2D g2d = (Graphics2D)g;
					dashPhase += 9.0f;
					BasicStroke dashedStroke = new BasicStroke(
							2f,
							BasicStroke.CAP_ROUND,
							BasicStroke.JOIN_MITER,
							1.5f, //miter limit
							new float[] {5.0f,5.0f},
							dashPhase
					);
					//g.setColor(Color.WHITE	);
					//g.fillRect(0,0,width,height);

					g2d.setColor(new Color(.2f, .2f, .5f, 1f));
					g2d.setStroke(dashedStroke);
					g2d.drawLine(building.getIntX(), building.getIntY(), building.getRallyPoint().ix, building.getRallyPoint().iy);
					
					
					g2d.setStroke(new BasicStroke());
					/*
					g.setColor(Color.BLUE);

					g.drawLine();
					 */
				}
			}

		}

		// Draw ground units
		flyingUnits = new LinkedList<Unit>();
		for(Unit unit : main.getUnits()) {
			if(!unit.hasFlying()) {


				BufferedImage sprite = unit.getSprite();
				int xDraw = (int)(unit.getX() - sprite.getWidth() / 2);
				int yDraw = (int)(unit.getY() - sprite.getHeight() / 2);
				g.drawImage(sprite, xDraw, yDraw, null);
				if(unit.isSelected()) { // Draw bars
					int dy = -7;
					for(Bar bar : unit.getBars()) {
						// Draw energy bar background
						g.setColor(Color.LIGHT_GRAY);
						g.fillRect(xDraw, yDraw + dy, unit.getWidth(), 5);
						// Draw energy bar content
						g.setColor(bar.getColor());
						g.fillRect(xDraw, yDraw + dy, (int)(bar.getPercentage() * unit.getWidth()), 5);
						// Draw energy bar contour
						g.setColor(Color.black);
						g.drawRect(xDraw, yDraw + dy, unit.getWidth(), 5);
						dy -= 7;
					}

				}
				if(unit.getCurrentAction() != null && !unit.getCurrentAction().isReady()) {
					double castPercentage = unit.getCastingPercentage();
					// Draw energy bar content
					g.setColor(Color.YELLOW);
					g.fillRect(xDraw, yDraw - 21, (int)(castPercentage * unit.getWidth()), 5);
					// Draw energy bar contour
					g.setColor(Color.black);
					g.drawRect(xDraw, yDraw - 21, unit.getWidth(), 5);
				}
			} else {
				flyingUnits.add(unit);
			}
		}

		/*
		for(ClickableObject clickableObj: main.getClickableObjects()) {
			BufferedImage sprite = clickableObj.getSprite();
			int xDraw = (int)(clickableObj.getX() - sprite.getWidth() / 2);
			int yDraw = (int)(clickableObj.getY() - sprite.getHeight() / 2);
			g.drawImage(sprite, xDraw, yDraw, null);
			if(clickableObj.isSelected()) {
				g.drawRect(xDraw, yDraw, sprite.getWidth(), sprite.getHeight());
			}
		}
		 */

		for(ManaCrystal manaCrystal : main.getManaCrystals()) {
			BufferedImage sprite = manaCrystal.getSprite();
			int xDraw = (int)(manaCrystal.getX() - sprite.getWidth() / 2);
			int yDraw = (int)(manaCrystal.getY() - sprite.getHeight() / 2);
			g.drawImage(sprite, xDraw, yDraw, null);
			if(manaCrystal.isSelected()) {
				g.drawRect(xDraw, yDraw, sprite.getWidth(), sprite.getHeight());
			}

		}



		for(AnimatedObject animObj : main.getAnimatedObjects()) {
			if(!animObj.disposed()) {
				BufferedImage sprite = animObj.getSprite();
				int xDraw = (int)(animObj.getX() - sprite.getWidth() / 2);
				int yDraw = (int)(animObj.getY() - sprite.getHeight() / 2);
				g.drawImage(sprite, xDraw, yDraw, null);
			}
		}




		for(Tree tree : main.getTrees()) {
			BufferedImage sprite = tree.getSprite();
			int xDraw = (int)(tree.getX() - sprite.getWidth() / 2);
			int yDraw = (int)(tree.getY() - sprite.getHeight() / 2);
			boolean hasUnitBehind = false;
			for(Unit unit : main.getUnits()) {
				if(Geometry.isInRectangle(unit.getIntX(), unit.getIntY(), tree.getIntX(), tree.getIntY(), tree.getRectangle())) {
					hasUnitBehind = true;
					break;
				}
			}
			if(hasUnitBehind) {
				float[] scales = {1f, 1f, 1f, 0.5f};
				float[] offsets = new float[4];
				RescaleOp rop = new RescaleOp(scales, offsets, null);

				// Draw the image, applying the filter 
				((Graphics2D)g).drawImage(sprite, rop, xDraw, yDraw);
				//g.drawImage(sprite, xDraw, yDraw, null);
			} else {
				g.drawImage(sprite, xDraw, yDraw, null);
			}
		}

		// Draw the sky map
		for(int i = 0; i < main.getMap().getTileHeight(); i++) {
			for(int j = 0; j < main.getMap().getTileWidth(); j++) {
				for(BufferedImage tile : main.getMap().getSkyLayerGraphicsAt(i, j)) {
					g.drawImage(tile, j * main.getMap().getTileSize(), i * main.getMap().getTileSize(), null);
				}
			}
		}


		// Draw flying units
		for(Unit unit : flyingUnits) {
			BufferedImage sprite = unit.getSprite();
			int xDraw = (int)(unit.getX() - sprite.getWidth() / 2);
			int yDraw = (int)(unit.getY() - sprite.getHeight() / 2);
			g.drawImage(sprite, xDraw, yDraw, null);
			if(unit.isSelected()) { // Draw bars
				int dy = -7;
				for(Bar bar : unit.getBars()) {
					// Draw energy bar background
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(xDraw, yDraw + dy, unit.getWidth(), 5);
					// Draw energy bar content
					g.setColor(bar.getColor());
					g.fillRect(xDraw, yDraw + dy, (int)(bar.getPercentage() * unit.getWidth()), 5);
					// Draw energy bar contour
					g.setColor(Color.black);
					g.drawRect(xDraw, yDraw + dy, unit.getWidth(), 5);
					dy -= 7;
				}

			}
			if(unit.getCurrentAction() != null && !unit.getCurrentAction().isReady()) {
				double castPercentage = unit.getCastingPercentage();
				// Draw energy bar content
				g.setColor(Color.YELLOW);
				g.fillRect(xDraw, yDraw - 21, (int)(castPercentage * unit.getWidth()), 5);
				// Draw energy bar contour
				g.setColor(Color.black);
				g.drawRect(xDraw, yDraw - 21, unit.getWidth(), 5);
			}

		}
		
		for(MovingObject mo : main.movingObjects) {
			g.drawImage(mo.getSprite(), mo.getIntX(), mo.getIntY(), null);
		}

		// Draw selection rectangle
		if(isButtonPressed) {
			int x = Math.min(mx, selectStartX);
			int y = Math.min(my, selectStartY);
			int width = Math.abs(selectStartX - mx);
			int height =  Math.abs(selectStartY - my);
			g.setColor(new Color(.2f, .2f, .5f, .4f));
			g.fillRect(x, y, width, height);
			g.setColor(new Color(.2f, .2f, .5f, 1));
			g.drawRect(x, y, width, height);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}