package Game;
import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.print.attribute.standard.Media;
import javax.swing.JFrame;

import Units.Caster;
import Units.Collector;
import Units.Summoner;
import Units.Unit;

import Abilities.Ability;
import Buildings.Building;
import Buildings.ManaAltar;
import GUI.ControlPanel;
import GUI.GamePanel;
import GUI.MainFrame;
import GUI.UpperPanel;
import GameObjects.AnimatedObject;
import GameObjects.ClickableObject;
import GameObjects.ManaCrystal;
import GameObjects.MovingObject;
import GameObjects.SelectableObject;
import GameObjects.Tree;
import Geometry.Geometry;
import Geometry.Point;
import Geometry.Point;
import Geometry.RangeQueryTree;
import Geometry.XTreeSet;
import IO.SpriteLoader;

// Test
public class Main implements KeyListener {

	private static final int delay = 10;

	public static void main(String[] args) throws InterruptedException {
		Main main = new Main();
		while(true) {
			for(Unit unit : main.getUnits()) {
				unit.updateAction();
				unit.updateGraphics();
			}
			for(Building building : main.getBuildings()) {
				building.updateGraphics();
			}
			for(AnimatedObject animObj : main.getAnimatedObjects()) {
				animObj.updateGraphics();
			}
			for(ManaCrystal manaCrystal : main.getManaCrystals()) {
				manaCrystal.updateGraphics();
			}
			for(MovingObject movObj : main.movingObjects) {
				movObj.updatePosition();
				movObj.updateGraphics();
				System.out.println(movObj.getIntX() + " " + movObj.getIntY());
			}
			/*
			LinkedList<AnimatedObject> deadAnimatedObjects = new LinkedList<AnimatedObject>();
				animObj.addTime(delay);
				if(animObj.getState() == State.DEAD) {
					deadAnimatedObjects.add(animObj);
				}
			}
			for(AnimatedObject animObj : deadAnimatedObjects) {
				main.getAnimatedObjects().remove(animObj);
			}
			 */
			Thread.sleep(delay);
		}
	}

	private MainFrame mainFrame;
	private GamePanel gamePanel;
	private ControlPanel controlPanel;

	private LinkedList<Unit> units;
	private LinkedList<Building> buildings;
	private LinkedList<AnimatedObject> animatedObjects;
	private LinkedList<ManaCrystal> manaCrystals;
	private LinkedList<ClickableObject> clickableObjects;
	public LinkedList<MovingObject> movingObjects;
	
	private LinkedList<Tree> trees;
	private Map map;
	private LinkedList<ClickableObject> selection;

	private SelectionManager selectionManager;
	
	public Main() {
		movingObjects = new LinkedList<MovingObject>();
		
		units = new LinkedList<Unit>();
		units.add(new Summoner(50, 50));
		units.add(new Summoner(100, 100));
		units.add(new Collector(200, 50));
		units.add(new Collector(200, 100));
		
		buildings = new LinkedList<Building>();
		buildings.add(new ManaAltar(100, 100));

		buildings.add(new ManaAltar(400, 200));
		animatedObjects = new LinkedList<AnimatedObject>();
		
		trees = new LinkedList<Tree>();
		trees.add(new Tree(200, 200, SpriteLoader.loadAnimationFile("tree1")));
		trees.add(new Tree(250, 250, SpriteLoader.loadAnimationFile("tree1")));
		trees.add(new Tree(150, 250, SpriteLoader.loadAnimationFile("tree1")));

		manaCrystals = new LinkedList<ManaCrystal>();
		manaCrystals.add(new ManaCrystal(600, 100, SpriteLoader.loadAnimationFile("crystal")));
		
		clickableObjects = new LinkedList<ClickableObject>();
		for(Building building : buildings) {
			clickableObjects.add(building);
		}
		
		selectionManager = new SelectionManager();
		//selection = new LinkedList<ClickableObject>();
		map = new Map("map1");
		initializeGUI();
	}

	private void initializeGUI() {
		mainFrame = new MainFrame("AnimationTest");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		gamePanel = new GamePanel(this);
		mainFrame.add(gamePanel);
		controlPanel = new ControlPanel(this);
		mainFrame.add(controlPanel, BorderLayout.SOUTH);
		mainFrame.setVisible(true);
		mainFrame.add(new UpperPanel(), BorderLayout.NORTH);
		/* --Full Screen--
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		GraphicsDevice myDevice = gs[0];
		System.out.println(gs.length);
		Window myWindow = new Window(mainFrame);
		try {
		    myDevice.setFullScreenWindow(myWindow);
		    System.out.println("OK");
		} finally {
		    myDevice.setFullScreenWindow(null);
		}
		*/
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	public LinkedList<Unit> getUnits() {
		return units;
	}

	public LinkedList<Tree> getTrees() {
		return trees;
	}
	
	public LinkedList<AnimatedObject> getAnimatedObjects() {
		return animatedObjects;
	}

	public void addAnimatedObject(AnimatedObject animatedObject) {
		animatedObjects.add(animatedObject);
	}

	public LinkedList<ClickableObject> getClickableObjects() {
		return clickableObjects;
	}

	public Map getMap() {
		return map;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyCode());
	}

	public LinkedList<Building> getBuildings() {
		return buildings;
	}
	
	public SelectionManager getSelectionManager() {
		return selectionManager;
	}

	public void addToSelection(ClickableObject selectable) {
		selectionManager.addToSelection(selectable);
		controlPanel.updateButtonPanel(selectionManager);
		controlPanel.repaint();
	}

	public void clearSelection() {
		selectionManager.clearSelection();
	}

	public void setSelection(LinkedList<ClickableObject> selection) {
		selectionManager.setSelection(selection);
		controlPanel.updateButtonPanel(selectionManager);
		controlPanel.repaint();
	}

	public void setSelection(ClickableObject selected) {
		selectionManager.setSelection(selected);
		controlPanel.updateButtonPanel(selectionManager);
		controlPanel.repaint();
	}


	public LinkedList<ClickableObject> getSelection() {
		return selectionManager.getSelection();
	}

	public boolean hasAbilitySelected() {
		return controlPanel.hasAbilitySelected();
	}

	public Ability getSelectedAbility() {
		return controlPanel.getSelectedAbility();
	}

	public void clearSelectedAbility() {
		controlPanel.clearSelectedAbility();
	}

	public ClickableObject getObjectAt(Point point) {
		for(ClickableObject object : clickableObjects) {
			if(Geometry.isInRectangle(point.ix, point.iy, object.getIntX(), object.getIntY(), object.getRectangle())) {
				return object;
			}
		}
		for(Building building : buildings) {
			if(Geometry.isInRectangle(point.ix, point.iy, building.getIntX(), building.getIntY(), building.getRectangle())) {
				return building;
			}
		}
		for(ManaCrystal manaCrystal : manaCrystals) {
			if(Geometry.isInRectangle(point.ix, point.iy, manaCrystal.getIntX(), manaCrystal.getIntY(), manaCrystal.getRectangle())) {
				return manaCrystal;
			}
	
		}
		return null;
	}
	
	public LinkedList<ManaCrystal> getManaCrystals() {
		return manaCrystals;
	}
	
	public ManaCrystal getClosestCrystal(Point point) {
		double min = Double.POSITIVE_INFINITY;
		ManaCrystal closestManaCrystal = null;
		for(ManaCrystal manaCrystal : manaCrystals) {
			if(manaCrystal instanceof ManaCrystal) {
				if(Geometry.squareDistance(point, manaCrystal.getPoint()) < min) {
					min = Geometry.squareDistance(point, manaCrystal.getPoint());
					closestManaCrystal = manaCrystal;
				}
			}
		}
		return closestManaCrystal;
	}
	
	public ManaAltar getClosestManaAltar(Point point) {
		ManaAltar closestManaAltar = null;
		double distance = Double.POSITIVE_INFINITY;
		for(Building building : buildings) {
			if(building instanceof ManaAltar) {
				Point manaAltarPos = new Point(building.getIntX(), building.getIntY());
				if(distance > Geometry.squareDistance(manaAltarPos, point)) {
					distance = Geometry.squareDistance(manaAltarPos, point);
					closestManaAltar = (ManaAltar)building;
				}
			}
		}
		return closestManaAltar;
	}

	public Caster getCorrespondingCaster(Ability ability) {
		Caster minMagicUnit = null;
		long minMagic = Integer.MAX_VALUE;
		for(ClickableObject object : selection) {
			// TODO: change this, we only want casters here
			Caster caster = (Caster)object;
			long casterMagic = caster.getMagicBar().getValue();
			if(caster.getAbilities().contains(ability) && casterMagic >= ability.getMagicCost() && casterMagic < minMagic) {
				minMagic = casterMagic;
				minMagicUnit = caster;
			}
		}
		return minMagicUnit;
	}
	
}
