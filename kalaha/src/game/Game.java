package game;

import java.util.ArrayList;

import db.Reader;
import db.Writer;

public class Game {
	
	private ArrayList<Container> containers = new ArrayList<>();
	private Distributor distributor;
	private int numberOfHouses;
	private int numberOfStones;
	private int storeIndexWest;
	private int storeIndexEast;
	private Store storeWest;
	private Store storeEast;
	private Reader reader;
	private Writer writer;
	
	public Game(int numberOfHouses, int numberOfStones) {
		
		this.numberOfHouses = numberOfHouses;
		this.numberOfStones = numberOfStones;
		this.storeIndexEast = numberOfHouses + 1;
		this.storeIndexWest = 2 * numberOfHouses + 1;
		
		// create houses for west palyer and add it to field
		for (int i = 1; i <= numberOfHouses; i++) {
			String id = "h_s_" + String.valueOf(i);
			House newHouse = new House(id, Side.SOUTH, numberOfStones);
			containers.add(newHouse);
		}
		
		// create store east and add it to field
		this.storeEast = new Store("store_east", Side.EAST, 0);
		containers.add(storeEast);
		
		// create houses for east player and add it to field
		for (int i = numberOfHouses; i > 0; i--) {
			String id = "h_n_" + String.valueOf(i);
			House newHouse = new House(id, Side.NORTH, numberOfHouses);
			containers.add(newHouse);
		}
		
		// create store west and add it to field
		this.storeWest = new Store("store_west", Side.WEST, 0);
		containers.add(storeWest);
		
		this.distributor = new Distributor(getContainers());
		
		reader = new Reader();
		writer = new Writer();
	}
	
	public Game(int[] Houses) {
		this.numberOfHouses = Houses.length / 2;
		this.numberOfStones = Houses[0];
		this.storeIndexEast = Houses.length / 2;
		this.storeIndexWest = Houses.length + 1;
		
		for (int i = 0; i < Houses.length/2; i++) {
			containers.add(new House("h_s_" + i, Side.SOUTH, Houses[i]));
		}
		
		this.storeEast = new Store("store_east", Side.EAST, 0);
		containers.add(storeEast);
		
		for (int i = Houses.length-1; i > Houses.length/2-1; i--) {
			containers.add(new House("h_n_" + i, Side.SOUTH, Houses[i]));
		}
		
		this.storeWest = new Store("store_west", Side.WEST, 0);
		containers.add(storeWest);
		
		this.distributor = new Distributor(getContainers());
		
		reader = new Reader();
		writer = new Writer();
	}
	
	public ArrayList<Container> getContainers() {
		return containers;
	}
	
	public Distributor getDistributor() {
		return distributor;
	}
	
	public int getStoreIndexWest() {
		return storeIndexWest;
	}
	
	public int getStoreIndexEast() {
		return storeIndexEast;
	}
	
	public void printGame() {
		System.out.println(" <-- North");
		System.out.print("   ");
		for (int i = 2 * numberOfHouses; i > numberOfHouses; i--) {
			System.out.print(" | " + containers.get(i).getNumberOfStones() + " | "); 
		}
		System.out.print("\n");
		System.out.println(containers.get(getStoreIndexWest()).getNumberOfStones() + "\t\t\t\t\t\t" + containers.get(getStoreIndexEast()).getNumberOfStones());
		System.out.print("   ");
		for (int i = 0; i < numberOfHouses; i++) {
			System.out.print(" | " + containers.get(i).getNumberOfStones() + " | "); 
		}
		System.out.println("\nSouth -->");
	}
	
	public ArrayList<Integer> getPlayableHouses(Side playerSide) {
		
		ArrayList<Integer> indexOfPlayableHouses = new ArrayList<>();
		Side sideOfHouses;
		
		if (playerSide == Side.EAST) {
			sideOfHouses = Side.SOUTH;
		}
		else {
			sideOfHouses = Side.NORTH;
		}
		
		for (Container container : getContainers()) {
			if (container.getSideOfContainer() == sideOfHouses && container.getNumberOfStones() > 0) {
				indexOfPlayableHouses.add(getContainers().indexOf(container)+1);
			}
		}
		
		return indexOfPlayableHouses;
	}
	
	public void printGameStats() {
		for(Container container: getContainers()) {
			System.out.println("Id     :" + container.getId());
			System.out.println("Side   :" + container.getSideOfContainer());
			System.out.println("Stones :" + container.getNumberOfStones());
			System.out.println();
		}
	}
	
	public void printPlayableHouses(Side playerSide) {
		
		if (playerSide == Side.EAST) {
			System.out.println("Options of Player East:");
		}
		else if (playerSide == Side.WEST) {
			System.out.println("Options of Player West:");
		}
		System.out.println(getPlayableHouses(playerSide));
	}
	
	public void playTurn(Side side, int indexOfHouse) {
		if (side == Side.EAST) {
			if (indexOfHouse > 6) {
				System.out.println("Can not play Houses with index > 6");
			} else {
				System.out.println("Player EAST plays house " + indexOfHouse);
			}
		} 
		else if (side == Side.WEST) {
			if (indexOfHouse < 8 ) {
				System.out.println("Can not play Houses with index < 8");
			}
			System.out.println("Player WEST plays house " + indexOfHouse);
		}
		System.out.println("Before turn:");
		printGame();
		getDistributor().distributeStones((House) getContainers().get(indexOfHouse-1));
		System.out.println("After turn:");
		printGame();
	}
}
