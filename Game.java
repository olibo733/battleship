package battleships;

import java.util.*;

public class Game implements GameItem {
	protected String name;
	public static int playernbr = 2;
	public static char t;
	public static int p1Ships; //Player one ships
	public static int p2Ships; //Player two ships
	public static final int rowSize = 10;
	public static final int colSize = 10;
	public static String[][] grid1 = new String [rowSize][colSize];
	public static String[][] grid2 = new String [rowSize][colSize];
	public static String[][] grid3 = new String [rowSize][colSize];
	public static String[][] grid4 = new String [rowSize][colSize];
	public static String[][][] gridArray = {grid1, grid2, grid3, grid4};
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {		
		Game.runGame();
	}
	
    public Game(String name) {
        this.name = name;
    }
	
    public Game() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
        return name;
    }
	
	public static void runGame() {
		System.out.println("Welcome to Battle Ships!");
		System.out.println("========================");
		System.out.println();
		//Launches all methods for game
		
		//System.out.println("Board 1");
		//System.out.println();
		//System.out.println("Board 2");
		//gridMap(grid2);
		//Add player
		if(playernbr == 2) {
			for (int i = 0; i <= playernbr; i++) {
				for (int j = 1; j <= 2; j++) {
					i++;
					newPlayer(i, j);
				}
				//Launch addShipss
		
				attack(grid1);
			}
		}
	}

	public static void launchMap(String[][] grid) {
		//Creates and fills grid with tilde
		//Prints top row of grid
		System.out.print("   ");
		
		for(int i = 0; i < rowSize; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == " X ") {
					grid[i][j] = " O ";
				} else {
					grid[i][j] = " ~ ";
					if (j == 0)
						System.out.print(i + "|"+ grid[i][j]);
					else if (j == grid[i].length -1)
						System.out.print(grid[i][j] + "|" + i);
					else
						System.out.print(grid[i][j]);
				}
			}
			System.out.println();
		}
	}
	//Prints map with ships
    public static void printMap(String[][] grid) {

		System.out.print("   ");
		for(int i = 0; i < rowSize; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();

        //Middle section of Ocean Map
        for(int x = 0; x < grid.length; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < grid[x].length; y++){
                System.out.print(grid[x][y]);
            }

            System.out.println("|" + x);
        }
    }

	public static void addShips(String[][] grid) {
		p1Ships = 2;
		System.out.println();
		for (int i = 1; i <= p1Ships; i++) {
			System.out.println("Please enter the length of your " + i + " ship. (only 1-5)");
			int size = scan.nextInt();
			while (size > 5 || size < 0) {
				System.out.println("Please only use numbers between 1-5.");
				System.out.println("Try again: ");
				size = scan.nextInt();
			}
			
			System.out.println("Please enter X cooridinate for your " + i + " ship: ");
			int x = scan.nextInt();		
			System.out.println("Please enter Y cooridinate for your " + i + " ship: ");
			int y = scan.nextInt();
			
			//if ship is attempted to place outside of grid
			if (((x > colSize) || (x < 0)) || ((y > rowSize) || (y < 0))) {
				System.out.println("Not able to place ships outside of the grid.");
				i--;
			}
			
			else if ((grid[x][y]== " X ")) {
				System.out.println("You cannot place a ship on top of another.");
				i--;
			} else {
				//add if ship is placed closer than one grid from another ship
				
				System.out.println("Would you like to place the ship horizontally or vertically?");
				System.out.println("Answer H or V.");
				String dir = scan.next();
				
				while(!(dir.equals("h")) && !(dir.equals("H")) && !(dir.equals("v")) && !(dir.equals("V"))) {
					System.out.println("Incorrect input, only use H or V.");
					dir = scan.next();
				}
				
				if (dir.equals("h") || dir.equals("H")) {
					for (int j = 0; j < size; j++) {
						if (grid[x][y+j] == " X " ) {
							System.out.println("You cannot place a ship on top of another.");
							i--;
						} else if (((x > colSize) || (x < 0)) || ((y+j > rowSize) || (y+j < 0))) {
							System.out.println("Not able to place ships outside of the grid.");
							i--;
						} else {
							grid[x][y+j] = " X ";
						}
					}
				}
				
				//Bugg för vertical, fixa
				else if (dir.equals("v") || dir.equals("V")) {
					for (int j = 0; j < size; j++) {
						if (grid[x+j][y] == " X " ) {
							System.out.println("You cannot place a ship on top of another.");
							i--;
						} else if (((x+j > colSize) || (x+j < 0)) || ((y > rowSize) || (y < 0))) {
							System.out.println("Not able to place ships outside of the grid.");
							i--;
						} else {
							grid[x+j][y] = " X ";
						}
					}
				}
				printMap(grid);
			}
		}
	}
	
	public static void newPlayer(int nbr, int i) {
		String name = null;
		int score = 0;
		int hitrate = 0;
		Player player = new Player(name, score, hitrate);

		
		System.out.println("Player " + nbr + ", please enter your name: ");
		name = scan.next();
		player.setName(name);
		System.out.println();
		System.out.println("Welcome " + name + "!");
		
		//launches players maps
		launchMap(gridArray[i]);
		System.out.println();
		System.out.println("The seas are currently empty, get ready to place your ships, "+ name);
		addShips(gridArray[i]);
	}
	public static void CPlayer(){
		System.out.println("\nComputer is deploying ships");
        //Deploying five ships for computer
		int cships = 3;
        for (int i = 1; i <=cships;) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < rowSize) && (y >= 0 && y < colSize) && (grid3[x][y] == " "))
            {
                grid3[x][y] =   "x";
                System.out.println(i + ". ship DEPLOYED");
                i++;
            }
        }
    }


	public static void playerTurn() {
		
	}
	
	public static void attack(String[][] grid) {
			System.out.println("Player 1: Get ready!");
			System.out.println();
			System.out.println("Please enter x coordinate to attack: ");
			int x = scan.nextInt();
			System.out.println("Please enter y coordinate to attack: ");
			int y = scan.nextInt();
		
			if (grid[x][y] == " X ")
				hitAttack(x , y, grid);
			else if (grid[x][y] == " ~ ")
				missedAttack(x, y, grid);	
			else
				alreadyHit(x, y, grid);
	}

	public static void missedAttack(int x, int y, String[][] grid) {
		System.out.println("You missed!");
		grid[x][y] = " O ";
		printMap(grid);
	}
	
	public static void hitAttack(int x, int y, String[][] grid) {
		System.out.println("You hit a ship at grid: " + x + ", " + y);
		grid[x][y] = " @ ";
		printMap(grid);
		attack(grid);
	}
	
	public static void alreadyHit(int x, int y, String[][] grid) {
		System.out.println("You have already hit this position, try again.");
		printMap(grid);
		attack(grid);
	}
}