package checkers;

import javax.swing.JFrame;
import java.util.ArrayList;
import BoardGameEnvironment.User;

public class CheckersMain extends JFrame{
	public CheckersMain() {
		super("Checkers");
		CheckersBoard board = new CheckersBoard();
		board.initializeGameBoard();
		CheckersGame gameState = new CheckersGame(board, new User("playerone"), new User("playertwo"));
		gameState.board.getPiece(0, 1).convertToKing();
		gameState.board.getPiece(0, 3).convertToKing();
		gameState.board.getPiece(0, 5).convertToKing();
		gameState.board.getPiece(0, 7).convertToKing();
		gameState.board.getPiece(1, 0).convertToKing();
		gameState.board.getPiece(1, 2).convertToKing();
		gameState.board.getPiece(1, 4).convertToKing();
		gameState.board.getPiece(1, 6).convertToKing();
		gameState.board.getPiece(2, 1).convertToKing();
		gameState.board.getPiece(2, 3).convertToKing();
		gameState.board.getPiece(2, 5).convertToKing();
		gameState.board.getPiece(2, 7).convertToKing();
		
		gameState.board.getPiece(5, 0).convertToKing();
		gameState.board.getPiece(5, 2).convertToKing();
		gameState.board.getPiece(5, 4).convertToKing();
		gameState.board.getPiece(5, 6).convertToKing();
		gameState.board.getPiece(6, 1).convertToKing();
		gameState.board.getPiece(6, 3).convertToKing();
		gameState.board.getPiece(6, 5).convertToKing();
		gameState.board.getPiece(6, 7).convertToKing();
		gameState.board.getPiece(7, 0).convertToKing();
		gameState.board.getPiece(7, 2).convertToKing();
		gameState.board.getPiece(7, 4).convertToKing();
		gameState.board.getPiece(7, 6).convertToKing();
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
