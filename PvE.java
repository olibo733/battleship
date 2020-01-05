package battleships;

import java.awt.Point;
import java.util.*;
import java.lang.Math;

public class PvE extends Grid {
	public boolean Cplayer() {
			
		boolean validP = false;
		Random rand = new Random();

		while (!validP) {
			int x = rand.nextInt(10);
			int y = rand.nextInt(10);
			int r = rand.nextInt(2);
			boolean shipCol = false;
			
			String s = String.valueOf(x) + String.valueOf(y);
			
			if (validPlacement(s, ship)) {
				outerloop: for (int q = y; q < y + ship.getShipY(); q++) {
					for (int p = x; p < x + ship.getShipX(); p++) {
						if (shipCollision(p, q)) {
							shipCol = true;
							break outerloop;
						}
					}
				}

				if (!shipCol) {
					int w = 0;
					for (int l = y; l < y + ship.getShipY(); l++) {
						int z = 0;
						for (int k = x; k < x + ship.getShipX(); k++) {
							String n = String.valueOf(k) + String.valueOf(l);
							markBoard(n, ship.getArea()[w][z], 0);
							if (ship.getArea()[w][z].equals("#")) {
								ship.setShipC(n);
							}
							validP = true;
							z++;
						}
						w++;
					}
				}
			} else {
				if (r == 0) {
					ship.rotateShipCounterClockwise(ship);
				}
			}
		}
	
			public static boolean validPlacement(String s, BattleShip ship) {
				int x = Character.getNumericValue(s.charAt(0));
				int y = Character.getNumericValue(s.charAt(1));
				for (int k = x; k < x + ship.getShipX(); k++) {
					for (int l = y; l < y + ship.getShipY(); l++) {
						if (!validPlacements.contains(String.valueOf(k) + String.valueOf(l))) {
							return false;
						}
					}
				}
				return true;
			}
	}