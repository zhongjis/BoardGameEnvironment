package BoardGameEnvironment.Memory;

import java.awt.*;
import javax.swing.*;  
import java.awt.event.*;
import javax.swing.JOptionPane;
import BoardGameEnvironment.*;

public class MemoryBoardView extends JFrame implements ActionListener{  
	MemoryGame game;
	GameBoard board;
	JPanel p;
	JButton lastClickedButton;

	public MemoryBoardView(MemoryGame game) {  
		super("Memory");
		this.game = game;
		this.board = this.game.getGameBoard();

		this.p = new JPanel();// creating instance of JPanel

		setSize(500,300);//400 width and 500 height  
		setLayout(null);//using no layout managers  

		this.p.setSize(500, 200);
		this.PlacingTiles();

		add(p);
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

				this.p.add(btn);
			}
		}
	}

	public void showBoard() {
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

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
//				try {
//					//sleep .5 seconds
//					Thread.sleep(500);
//				} catch (InterruptedException hahahaha) {
//					System.out.println("not matched");
//				}
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
				break;
			default: 
				System.out.println("unexpected feedback, please check");
		}
	}
}