package BoardGameEnvironment.Memory;

import java.awt.*;
import javax.swing.*;  
import java.awt.event.*;
import javax.swing.JOptionPane;
import BoardGameEnvironment.*;

public class MemoryBoardView extends JFrame implements ActionListener{  
	MemoryGame game;
	GameBoard board;
	JPanel gameArea;
	JPanel statsArea;
	JButton lastClickedButton;
	JLabel turn, playerOneScore, playerTwoScore, winMessage;

	public MemoryBoardView(MemoryGame game) {  
		super("Memory");
		this.game = game;
		this.board = this.game.getGameBoard();

		this.gameArea = new JPanel();// creating instance of JPanel
		this.statsArea = new JPanel();

		setSize(500,500);
		setLayout(null);//using no layout managers  

		this.gameArea.setSize(500, 200);
		this.statsArea.setSize(500,100);
		this.PlacingTiles();
		winMessage = new JLabel("");

		this.setLayout(new BorderLayout());

		this.turn = new JLabel("Turn: " + Integer.toString(game.turn));
		this.playerOneScore = new JLabel("PlayerOneScore: 0");
		this.playerTwoScore = new JLabel("PlayerTwoScore: 0");

		statsArea.add(this.turn);
		statsArea.add(this.playerOneScore);
		statsArea.add(this.playerTwoScore);

		add(statsArea, BorderLayout.NORTH);
		add(gameArea, BorderLayout.CENTER);
	} 

	private void PlacingTiles() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {

				// create a new button
				JButton btn = new JButton(" ");
				String btn_command = Integer.toString(i) + " " + Integer.toString(j);
				// System.out.println(btn_command);
				btn.setActionCommand(btn_command);
				btn.addActionListener(this);

				this.gameArea.add(btn);
			}
		}
	}

	public void showBoard() {
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){

		// get button action and parse it for getting the row and column 
		JButton b = (JButton) e.getSource();
		String btn_command = b.getActionCommand();
		String[] splited = btn_command.split("\\s+");
		int row = Integer.parseInt(splited[0]);
		int column = Integer.parseInt(splited[1]);

		// System.out.println(row);
		// System.out.println(column);

		// flip action
		b.setEnabled(false);
		int cardNo = this.board.getPiece(row, column).getId();
		b.setText(Integer.toString(cardNo));

		// playMove
		int moveFeedBack = game.playMove(row, column);
		System.out.println(moveFeedBack);
		
		switch (moveFeedBack) {
			case 1:
				lastClickedButton = null;
				break;
			case 2:
				lastClickedButton.setEnabled(true);

				lastClickedButton.setText(" ");
				b.setEnabled(true);
				b.setText(" ");
				lastClickedButton = null;
				break;
			case 3:
				lastClickedButton = (JButton) e.getSource();
				break;
			case 4:
				// game over
				System.out.println("game over!");
				lastClickedButton = null;
				if (game.getWinner() == 0) {
					String playerOneName = game.getPlayer(0).getName();
					winMessage.setText(playerOneName + " wins!");
				} else if (game.getWinner() == 1) {
					String playerTwoName = game.getPlayer(1).getName();
					winMessage.setText(playerTwoName + " wins!");
				}
				statsArea.add(winMessage);
				break;
			default: 
				System.out.println("unexpected feedback, please check");
		}
		this.statsUpdate();
	}

	private void  statsUpdate() {
		if (game.turn == 0) {
			this.turn.setText("Turn: PlayerOne");
		} else if (game.turn == 1) {
			this.turn.setText("Turn: PlayerTwo");
		}

		String scoreOne = Integer.toString(game.getPlayerOneScore());
		String scoreTwo = Integer.toString(game.getPlayerTwoScore());
		this.playerOneScore.setText("PlayerOneScore: " + scoreOne);
		this.playerTwoScore.setText("PlayerTwoScore: " + scoreTwo);
	}
}