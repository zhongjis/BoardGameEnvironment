package BoardGameEnvironment.Checkers;

import javax.swing.JFrame;
import java.util.ArrayList;

import BoardGameEnvironment.User;
//import main.java.BoardGameEnvironment.User;

public class CheckersMain extends JFrame{
	public CheckersMain(User userOne, User userTwo) {
		super("Checkers");
		CheckersBoard board = new CheckersBoard();
		board.initializeGameBoard();
		CheckersGame gameState = new CheckersGame(board, userOne, userTwo);
		CheckersBoardView gameView = new CheckersBoardView(gameState);
		setContentPane(gameView);
		setResizable(false);
		pack();
		setVisible(true);
	}
	
//	public static void main(String[] args)
//	{
//		CheckersMain run = new CheckersMain();
//	}
}
