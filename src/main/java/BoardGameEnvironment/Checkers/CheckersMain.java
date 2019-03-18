package main.java.BoardGameEnvironment.Checkers;

import javax.swing.JFrame;
import java.util.ArrayList;
import main.java.BoardGameEnvironment.User;

public class CheckersMain extends JFrame{
	public CheckersMain() {
		super("Checkers");
		CheckersBoard board = new CheckersBoard();
		board.initializeGameBoard();
		CheckersGame gameState = new CheckersGame(board, new User("playerone"), new User("playertwo"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		CheckersBoardView gameView = new CheckersBoardView(gameState);
		setContentPane(gameView);
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		CheckersMain run = new CheckersMain();
	}
}
