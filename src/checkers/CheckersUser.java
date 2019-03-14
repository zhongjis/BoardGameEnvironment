package checkers;
import BoardGameEnvironment.User;

public class CheckersUser extends User{

	private int pieces = 12;
	
	public CheckersUser(String name, int player) {
		super(name);
		super.playerNumber = player;	
	}
	
	public int getPieces() {
		return pieces;
	}
	
	public void decrementPieces() {
		pieces--;
	}
}
