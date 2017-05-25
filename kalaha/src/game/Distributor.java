package game;

import java.util.ArrayList;

public class Distributor {
	
	private ArrayList<Container> containers;
	private int indexOfInitialHouse;
	private int indexOfNextContainer;
	private int stonesToDistribute;
	private Side sideOfInitialContainer;
	private boolean canPlayAgain;

	public Distributor(ArrayList<Container> containers) {
		this.containers = containers;
		this.stonesToDistribute = 0;
	}
	public int getStonesToDistribute() {
		return stonesToDistribute;
	}
	
	public ArrayList<Container> getContainers() {
		return containers;
	}
	
	public void setStonesToDistribute(int stonesToDistribute) {
		this.stonesToDistribute = stonesToDistribute;
	}
	
	public void decrementStonesToDistribute() {
		stonesToDistribute--;
	}
	
	public void setCanPlayAgain(boolean canPlayAgain) {
		this.canPlayAgain = canPlayAgain;
	}
	
	public boolean isCanPlayAgain() {
		return canPlayAgain;
	}
	
	public void setSideOfInitialContainer(Side sideOfContainer) {
		this.sideOfInitialContainer = sideOfContainer;
	}
	
	public Side getSideOfInitialContainer() {
		return sideOfInitialContainer;
	}
	
	public void setHouseToDistribute(House house) {
		if (house.getNumberOfStones() < 1) {
			System.out.println("Can not distribute house with no stones");
			return;
		}
		setSideOfInitialContainer(house.getSideOfContainer());
		setStonesToDistribute(house.removeStones());
		setIndexOfInitialHouse(containers.indexOf(house));
		setIndexOfNextContainer(containers.indexOf(house));
		setCanPlayAgain(false);
	}
	
	public void setIndexOfInitialHouse(int indexOfHouse) {
		this.indexOfInitialHouse = indexOfHouse;
	}
	
	public void setIndexOfNextContainer(int indexOfNextHouse) {
		this.indexOfNextContainer = indexOfNextHouse;
	}
	
	public int getIndexOfInitialHouse() {
		return indexOfInitialHouse;
	}
	
	public int getIndexOfNextContainer() {
		return indexOfNextContainer;
	}
	
	public void distributeStones(House house) {
		setHouseToDistribute(house);
		
		if (sideOfInitialContainer == Side.SOUTH) {
			distributePlayerEast();
		}
		else {
			distributePlayerWest();
		}
		
	}
	
	public Container getNextContainer() {
		// returns only playable containers: own store, own house or opponents house
		int indexOfNextHouse = (getIndexOfNextContainer()+1) % 13;
		setIndexOfNextContainer(indexOfNextHouse);
		Container nextContainer = containers.get(getIndexOfNextContainer());
		// case: store
		if (nextContainer instanceof Store) {
			// case: own house of player east
			if (sideOfInitialContainer == Side.SOUTH && nextContainer.getSideOfContainer() == Side.EAST) {
				Store storeOfPlayer = (Store) nextContainer;
				return storeOfPlayer;
			}
			// case: own house of player west
			else if (sideOfInitialContainer == Side.NORTH && nextContainer.getSideOfContainer() == Side.WEST) {
				Store storeOfPlayer = (Store) nextContainer;
				return storeOfPlayer;
			}
			// case: opponents stors -> get next playable container
			else {
				return getNextContainer();
			}
		}
		// case: house
		else {
			House nextHouse = (House) nextContainer;
			// case: own house
			if (sideOfInitialContainer == nextHouse.getSideOfContainer()) {
				// case: own house is blocked
				if (nextHouse.isBlocked()) {
					return getNextContainer();
				}
				// case: own house can be played
				else {
					return nextHouse;
				}
			}
			// case: opponents house
			return nextHouse;
			
		}
		
	}
	
	public void distributePlayerWest() {
		while (getStonesToDistribute() > 0) {
			Container containerToBePlayed = getNextContainer();
			// case: not the last stone
			if (getStonesToDistribute() > 1) {
				// place stone and continue with next stone
				containerToBePlayed.incrementStones();
				decrementStonesToDistribute();
				distributePlayerWest();
			}
			// case: last stone
			else {
				// case: own store -> can play again
				if (containerToBePlayed instanceof Store) {
					containerToBePlayed.incrementStones();
					decrementStonesToDistribute();
					setCanPlayAgain(true);
				}
				else {
					House houseToBePlayed = (House) containerToBePlayed;
					// case: own house
					if (houseToBePlayed.getSideOfContainer() == getSideOfInitialContainer()) {
						// case: house is empty -> get stones of opposite side
						if (houseToBePlayed.getNumberOfStones() == 0) {
							 int indexOfOppositeHouse = getContainers().size() - getIndexOfInitialHouse() - 1;
							 House oppositeHouse = (House) getContainers().get(indexOfOppositeHouse);
							 int removedStones = oppositeHouse.removeStones();
							// <specific to east player>
							 ((Store) getContainers().get(13)).incrementStones(removedStones);
							 decrementStonesToDistribute();
							 setCanPlayAgain(false);
						}
						// case: house is not empty -> put stone
						else {
							containerToBePlayed.incrementStones();
							decrementStonesToDistribute();
							setCanPlayAgain(false);
						}
					}
					// case: opponent house
					else {
						// case: contains 2 or 3 stones
						if (houseToBePlayed.getNumberOfStones() == 1 || houseToBePlayed.getNumberOfStones() == 2) {
							int removedStones = houseToBePlayed.removeStones() + 1;
							// <specific to west player>
							((Store) getContainers().get(13)).incrementStones(removedStones);
							decrementStonesToDistribute();
							setCanPlayAgain(false);
						}
						// case: otherwise
						else {
							containerToBePlayed.incrementStones();
							decrementStonesToDistribute();
							setCanPlayAgain(false);
						}
					}
				}
			}
		}
	}
	
	public void distributePlayerEast() {
		while (getStonesToDistribute() > 0) {
			Container containerToBePlayed = getNextContainer();
			// case: not the last stone
			if (getStonesToDistribute() > 1) {
				// place stone and continue with next stone
				containerToBePlayed.incrementStones();
				decrementStonesToDistribute();
				distributePlayerEast();
			}
			// case: last stone
			else {
				// case: own store -> can play again
				if (containerToBePlayed instanceof Store) {
					containerToBePlayed.incrementStones();
					decrementStonesToDistribute();
					setCanPlayAgain(true);
				}
				else {
					House houseToBePlayed = (House) containerToBePlayed;
					// case: own house
					if (houseToBePlayed.getSideOfContainer() == getSideOfInitialContainer()) {
						// case: house is empty -> get stones of opposite side
						if (houseToBePlayed.getNumberOfStones() == 0) {
							 int indexOfOppositeHouse = getContainers().size() - getIndexOfInitialHouse() -1;
							 House oppositeHouse = (House) getContainers().get(indexOfOppositeHouse);
							 int removedStones = oppositeHouse.removeStones();
							 // <specific to east player>
							 ((Store) getContainers().get(6)).incrementStones(removedStones);
							 decrementStonesToDistribute();
							 setCanPlayAgain(false);
						}
						// case: house is not empty -> put stone
						else {
							containerToBePlayed.incrementStones();
							decrementStonesToDistribute();
							setCanPlayAgain(false);
						}
					}
					// case: opponent house
					else {
						// case: contains 2 or 3 stones
						if (houseToBePlayed.getNumberOfStones() == 1 || houseToBePlayed.getNumberOfStones() == 2) {
							int removedStones = houseToBePlayed.removeStones() + 1;
							// <specific to east player>
							((Store) getContainers().get(6)).incrementStones(removedStones);
							decrementStonesToDistribute();
							setCanPlayAgain(false);
						}
						// case: otherwise
						else {
							containerToBePlayed.incrementStones();
							decrementStonesToDistribute();
							setCanPlayAgain(false);
						}
					}
				}
			}
		}
	}
}
