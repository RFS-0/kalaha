package game;

public class Store extends Container {
	
	public Store(String id, Side sideOfStore, int numberOfStones) {
		super(id, sideOfStore, numberOfStones);
	}
	
	public void incrementStones(int numberOfStones) {
		this.numberOfStones += numberOfStones;
	}
	
}
