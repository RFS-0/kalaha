package game;

public class House extends Container {

	private boolean blocked;
	
	public House(String id, Side sideOfContainer, int numberOfStones) {
		super(id, sideOfContainer, numberOfStones);
		this.blocked = false;
	}
	
	public boolean canBePlayed() {
		if (numberOfStones == 0) {
			return false;
		}
		return true;
	}
	
	public int removeStones() {
		if (numberOfStones == 0) {
			System.out.println("Can not remove stones from empty house!");
		}
		int numberOfStones = this.numberOfStones;
		this.numberOfStones = 0;
		block();
		return numberOfStones;
	}
	
	public void block() {
		blocked = true;
	}
	
	public void unblock() {
		blocked = false;
	}
	
	public boolean isBlocked() {
		return blocked;
	}
	
}
