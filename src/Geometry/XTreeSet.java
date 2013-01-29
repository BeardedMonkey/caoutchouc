package Geometry;

import GameObjects.GameObject;

import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;


public class XTreeSet<E extends GameObject> implements RangeQueryTree<E> {

	private TreeMap<Integer, E> xTree;
	
	public XTreeSet() {
		xTree = new TreeMap<Integer, E>();
	}
	
	@Override
	public LinkedList<E> getObjectsInRange(int x1, int y1, int x2, int y2) {
		SortedMap<Integer, E> xRange = xTree.subMap(x1, x2);
		LinkedList<E> inRange = new LinkedList<E>();
		for(Entry<Integer, E> e : xRange.entrySet()) {
			int y = e.getValue().getIntY();
			if(y1 <= y && y <= y2) {
				inRange.add(e.getValue());
			}
		}
		return inRange;
	}

	@Override
	public void add(E elem) {
		xTree.put(elem.getIntX(), elem);
	}
	
	public void remove(E elem) {
		// TODO
	}


}
