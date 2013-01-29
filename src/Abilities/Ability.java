package Abilities;

import javax.swing.ImageIcon;

import Game.Animation;

public abstract class Ability {

	// Ability names must be unique because they
	// are used as id's.
	private String name; 
	private ImageIcon iconSprite;
	private int rangeRadius;
	private int damage;
	private int minCastDistance;
	private int magicCost;
	private int castTime;
	private Animation animation;
	
	public Ability(String name, ImageIcon iconSprite, int rangeRadius, int damage, int minCastDistance, int magicCost, int castTime, Animation animation) {
		this.name = name;
		this.iconSprite = iconSprite;
		this.rangeRadius = rangeRadius;
		this.damage = damage;
		this.minCastDistance = minCastDistance;
		this.magicCost = magicCost;
		this.castTime = castTime;
		this.animation = animation;
	}
	
	public Ability(String name, ImageIcon iconSprite) {
		
	}
 	
	public String getName() {
		return name;
	}
	
	public int getMagicCost() {
		return magicCost;
	}
	
	public int getCastTime() {
		return castTime;
	}
	
	public ImageIcon getIcon() {
		return iconSprite;
	}
	
	public int getRangeRadius() {
		return rangeRadius;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public Animation getAnimation() {
		return animation;
	}
	
}
