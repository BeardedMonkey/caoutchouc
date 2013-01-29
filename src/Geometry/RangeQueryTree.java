package Geometry;

import GameObjects.GameObject;

import java.util.LinkedList;


public interface RangeQueryTree<E extends GameObject> {

	public void add(E elem);
	
	public LinkedList<E> getObjectsInRange(int x1, int y1, int x2, int y2);
	
	public void remove(E elem);
	
}
