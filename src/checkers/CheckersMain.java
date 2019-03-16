package checkers;

import javax.swing.JFrame;
import java.util.ArrayList;
import BoardGameEnvironment.User;

public class CheckersMain extends JFrame{
	public CheckersMain() {
		super("Checkers");
		CheckersBoard board = new CheckersBoard();
		CheckersGame gameState = new CheckersGame(board, new User("playerone"), new User("playertwo"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		CheckersView gameView = new CheckersView(gameState);
		setContentPane(gameView);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		CheckersMain run = new CheckersMain();
	}
}
