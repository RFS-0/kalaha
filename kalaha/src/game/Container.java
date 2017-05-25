package game;

public abstract class Container {
	
	protected String id;
	protected Side sideOfContainer;
	protected int numberOfStones;
	
	public Container(String id, Side sideOfPlayer, int numberOfStones) {
		this.id = id;
		this.sideOfContainer = sideOfPlayer;
		this.numberOfStones = numberOfStones;
	}
	
	public String getId() {
		return id;
	}
	
	public void incrementStones() {
		this.numberOfStones++;
	}
	
	public int getNumberOfStones() {
		return numberOfStones;
	}
	
	public Side getSideOfContainer() {
		return sideOfContainer;
	}
	

}
