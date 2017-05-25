package kalaha;

import game.Game;
import game.Side;

public class Main {
	
	public static void main(String[] args) {
		
		int[] Houses = {0,0,0,0,0,1,0,0,0,0,0,0};
		Game game = new Game(Houses);
		game.printGame();
		
	}

}
