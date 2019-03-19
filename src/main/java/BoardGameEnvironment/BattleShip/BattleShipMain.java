package BoardGameEnvironment;

import javax.swing.JFrame;

import BoardGameEnvironment.User;
//import main.java.BoardGameEnvironment.User;

public class BattleShipMain extends JFrame{
	public BattleShipMain(User userOne, User userTwo) 
		{
		super("BattleShip!");
		BattleShip board = new BattleShip(userOne, userTwo);
		board.initializeGameBoard();
		BattleShipView gameView = new BattleShipView(board);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(gameView);
		setResizable(false);
		pack();
		setVisible(true);
	}
	

}