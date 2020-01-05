package battleships;

import java.util.*;

public class Game  {
	public LinkedList<Player> players = new LinkedList<Player>();
	int turnCounter = 1;
	static Scanner scan = new Scanner(System.in);
	
		
	
	
	public void runGame() {
		System.out.println("Welcome to Battle Ships!");
		System.out.println("========================");
		System.out.println();
		System.out.println("Vill du spela mot");
		System.out.println("1. En vän?");
		System.out.println("2. En dator?");
		int playernbr;
		int val = scan.nextInt();
		Game game = new Game();
		switch(val) {
		case 1:
			playernbr = 2;
			game.newPlayer(2);
			game.play();
			
		case 2:
			playernbr = 1;
			game.newPlayer(1);
			game.newComputerPlayer(2);
			//game.play();
			
		
		default:
			System.out.println("Vänligen mata in ett värde mellan 1 och 2");
			
		}
	}
	
   
	public void newPlayer(int playernbr) {	
		for (int i = 1; i <= playernbr; i++) {
			String name = null;
			int score = 0;
			int hitrate = 0;
			int damagerate = 0;
			int id = 0;
			int health = 0;
			Player player = new Player(name, health, id, hitrate, damagerate);
			players.add(player);	
			System.out.println("Player " + i + ", please enter your name: ");
			name = scan.next();
			player.setName(name);
			player.setID(i);
			System.out.println();
			Constants.printEmpty();
			System.out.println("Welcome " + player.getName() + "!");
			player.makeGrid();
			Constants.printEmpty();
		}
	}
	public void newComputerPlayer(int playernbr) {
		for (int i = 2; i <= 2; i++) {//Kanske deleteus
			String name = null;
			int score = 0;
			int hitrate = 0;
			int damagerate = 0;
			int id = 0;
			int health = 0;
			Player player = new Player(name, health, id, hitrate, damagerate);
			players.add(player);
			//System.out.println("Player " + i + ", please enter your name: ");
			name = ("ComputerMan");
			player.setName(name);
			player.setID(2);
			System.out.println();
			Constants.printEmpty();
			System.out.println("Welcome " + player.getName() + "!");
			System.out.println();
			player.makeComputerGrid();
			Constants.printEmpty();
			
		}
	}
	
	public boolean gameOver() {
		for(Player p : players) {
			if(p.getHealth() == 0) {
				return false;
			}
		}
		return true;
	}
	
	public Player getOpponent() {
		for(Player p : players) {
			if(p.getID() != turnCounter) {
				return p;
			}
		}
		return null;
	}
	
    public void play() {
    		while(gameOver()) {
    			for(Player p : players) {
    				if(p.getID() == turnCounter) {
    					p.turnToPlay(getOpponent());
    				}
    			}
    			turnCounter++;
    			if (turnCounter > 2) turnCounter = 1;
    			
    		}
    }
}