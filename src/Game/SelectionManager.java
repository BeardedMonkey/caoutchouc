package Game;

import java.util.LinkedList;

import Buildings.Building;
import GameObjects.ClickableObject;

public class SelectionManager {

	private LinkedList<ClickableObject> selection;

	public SelectionManager() {
		selection = new LinkedList<ClickableObject>();
	}

	public void clearSelection() {
		for(ClickableObject selectedObject : selection) {
			System.out.println(selectedObject);
			selectedObject.setSelected(false);
		}
		selection.clear();
	}

	public boolean isSingle() {
		return selection.size() == 1;
	}

	public boolean isBuilding() {
		return isSingle() && selection.getFirst() instanceof Building;
	}
	
	public void setSelection(ClickableObject selectedObject) {
		clearSelection();
		selection.add(selectedObject);
		selectedObject.setSelected(true);
	}
	
	public void setSelection(LinkedList<ClickableObject> selectedObjects) {
		clearSelection();
		selection = selectedObjects;
		for(ClickableObject selectedObject : selectedObjects) {
			selectedObject.setSelected(true);
		}
	}
	
	public void addToSelection(ClickableObject newObject) {
		selection.add(newObject);
		newObject.setSelected(true);
	}
	
	public LinkedList<ClickableObject> getSelection() {
		return selection;
	}
	
	public ClickableObject getFirst() {
		return selection.getFirst();
	}

}
