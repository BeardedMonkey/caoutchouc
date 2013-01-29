package Game;

public class Tuple<E, F> {
	
	private E X;
	private F Y;
	
	public Tuple(E entry1, F entry2) {
		X = entry1;
		Y = entry2;
	}
	
	public E getX() {
		return X;
	}
	
	public F getY() {
		return Y;
	}
	
}
