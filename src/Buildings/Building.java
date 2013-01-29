package Buildings;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Units.Unit;

import Abilities.Ability;
import Bars.BuildBar;
import Bars.EnergyBar;
import Game.Animation;
import Game.State;
import GameObjects.AnimatedObject;
import GameObjects.ClickableObject;
import GameObjects.GameObject;
import Geometry.Point;
import Geometry.Rect;

public abstract class Building extends ClickableObject {

	protected EnergyBar energyBar;
	protected BuildBar buildBar;
	protected Rect rectangle;
	protected BufferedImage faceSprite;
	protected Point rallyPoint;
	
	protected Queue<Unit> productionQueue;
	protected ArrayList<Unit> productionUnits;
	
	public Building(int x, int y, int maxEnergy, Animation animation) {
		super(x, y, animation);
		this.energyBar = new EnergyBar(maxEnergy);
		this.buildBar = new BuildBar();
		this.isSelected = false;
		this.state = State.PERFORMING_ACTION;
		abilities = new LinkedList<Ability>();
		bars.add(buildBar);
		bars.add(energyBar);
		
		productionQueue = new LinkedList<Unit>();
		productionUnits = new ArrayList<Unit>();
		
		setAbilities();
	}
	
	public abstract void setAbilities();

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public Queue<Unit> getProductionQueue() {
		return productionQueue;
	}
	
	public void setProductionUnits(ArrayList<Unit> productionUnits) {
		this.productionUnits = productionUnits;
	}
	
	public ArrayList<Unit> getProductionUnits() {
		return productionUnits;
	}
	
	public void setRallyPoint(Point rallyPoint) {
		this.rallyPoint = rallyPoint;
	}
	
	public Point getRallyPoint() {
		return rallyPoint;
	}
	
	public Rect getRect() {
		return rectangle;
	}
	
	public EnergyBar getEnergyBar() {
		return energyBar;
	}
	

	@Override
	public void setObjectBehind(GameObject object) {
		// TODO Auto-generated method stub
		
	}

	public void setBuildTime(double d) {
		buildBar.setPercentage(d);
	}

}
