package BoardGameEnvironment.Checkers;

import BoardGameEnvironment.User;

public class CheckersUser extends User {
	protected int pieces = 12;
	
	public CheckersUser(String name) {
		super(name);	
	}
	
	public int getPieces() {
		return pieces;
	}
	
	public void decrementPieces() {
		pieces--;
	}
}
