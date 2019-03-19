package main.java.BoardGameEnvironment.Checkers;

import javax.swing.JFrame;
import java.util.ArrayList;
import main.java.BoardGameEnvironment.User;

public class CheckersMain extends JFrame{
	public CheckersMain(User userOne, User userTwo) {
		super("Checkers");
		CheckersBoard board = new CheckersBoard();
		board.initializeGameBoard();
		CheckersGame gameState = new CheckersGame(board, userOne, userTwo);
		CheckersBoardView gameView = new CheckersBoardView(gameState);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(gameView);
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		CheckersMain run = new CheckersMain(new User("playerone"), new User("playertwo"));
	}
}
