/**
 * Do not import or declare any packages.
 * You are free to add fields/methods/interfaces/classes/etc., but do not alter the given code.
 * For your own safety, do not use any techniques not taught in this course so far.
 * 
 * @author <Your name here>
 */

public class GridGame {
	
	/*
	 * TODO: Fill in the constructor appropriately. 
	 * 'r' and 'c' are the numbers of rows and columns, respectively.
	 */
	public GridGame(int r, int c) {
	}
	
	/*
	 * TODO: Introduce a new player with 'name' at location (r, c).
	 * Return false if a same player already exists in the game, or there is someone else at (r, c).
	 */
	public boolean spawnPlayer(String name, int r, int c) {
		return false;
	}
	
	/*
	 * TODO: Remove player with 'name' from the game.
	 * Return false if there is no such player, and true otherwise.
	 */
	public boolean removePlayer(String name) {
		return false;
	}
	
	/*
	 * TODO: Remove whatever player that's at location (r, c).
	 * Return false if nobody's there.
	 */
	public boolean removeAt(int r, int c) {
		return false;
	}
	
	/*
	 * TODO: Move the given player (goes by 'name') to location (r,c).
	 * Move is invalid if 'name' doesn't exist or (r,c) is not 1 step away from 'name''s location.
	 * Wrap-around movements are also allowed.
	 * Return null for invalid moves.
	 * For valid moves, the player already existing in that location will be removed from the game
	 * and its name should be returned. Return null if no player exists at (r,c).
	 */
	public String moveTo(String name, int r, int c) {
		return null;
	}
	
	/*
	 * TODO: Return the name of the player at location (r, c).
	 * Return null if location is unoccupied.
	 */
	public String getPlayerAt(int r, int c) {
		return null;
	}
	
	/*
	 * TODO: Return the location of the player 'name'.
	 * Return null if no such player exists.
	 */
	public Location whereIs(String name) {
		return null;
	}
	
	/*
	 * TODO: Return an array of locations for all players currently in the game.
	 * Return null if there aren't any.
	 */
	public Location[] getAllOccupiedGrids() {
		return null;
	}
	
	/*
	 * TODO: Return an array of all players' names.
	 * Null if there aren't any.
	 */
	public String[] getAllPlayers() {
		return null;
	}
	
	/*
	 * The following main() method is entirely optional, and is given only as a reference. 
	 * These test cases give an example on how your code should work.
	 * You are free to do whatever you want to with this code. 
	 * In fact, you are encouraged to share test cases among your classmates. (But not the codes!)
	 * Notice that I am not testing all methods: try to come up with your own.
	 * This main() method will *not* be part of your grade.
	 */
	public static void main(String[] s) {
		GridGame g = new GridGame(10, 10); // Build a 10x10 grid
		System.out.println(g.spawnPlayer("Homer Simpson", 0, 1)); // Should print 'true'
		System.out.println(g.spawnPlayer("Marjorie Bouvier", 0, 1)); // 'false'
		g.spawnPlayer("Gerald Smith", 0, 0);
		g.spawnPlayer("Richard Sanchez", 5, 2);
		g.spawnPlayer("Bender Rodriguez", 2, 3);
		System.out.println(g.spawnPlayer("Mortimer Smith", 10, 0)); // 'false'
		
		String[] all = g.getAllPlayers();
		for(String str : all) 
			System.out.println(str); // "Homer Simpson", "Gerald Smith", "Richard Sanchez", "Bender Rodriguez" get printed out
		
		Location[] locs = g.getAllOccupiedGrids();
		for(Location l : locs) 
			System.out.println("(" + l.getRow() + ", " + l.getCol() + ")");
		
		System.out.println(g.moveTo("Homer Simpson", 0, 0)); // "Gerald Smith"
		Location l = g.whereIs("Homer Simpson");
		System.out.println("(" + l.getRow() + ", " + l.getCol() + ")"); // "(0, 0)"
		l = g.whereIs("Gerald Smith");
		assert(l == null);
	}
}

/*
 * Represents a location on a grid.
 */
abstract class Location {
	public abstract int getRow();
	public abstract int getCol();
}