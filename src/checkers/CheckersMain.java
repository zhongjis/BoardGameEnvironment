package checkers;

import java.util.ArrayList;
import BoardGameEnvironment.User;
public class CheckersMain {
	public static void main(String[] args)
	{
		CheckersBoard board = new CheckersBoard();
		CheckersGame gamestate = new CheckersGame(board, new User("playerone"), new User("playertwo"));
		gamestate.run();
	}
}
