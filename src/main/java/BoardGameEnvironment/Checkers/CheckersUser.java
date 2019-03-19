package main.java.BoardGameEnvironment.Checkers;
import main.java.BoardGameEnvironment.User;

public class CheckersUser extends User{
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
