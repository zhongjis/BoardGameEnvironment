package BoardGameEnvironment.Memory;

import java.awt.*;
import javax.swing.*;  
import java.awt.event.*;  
import javax.swing.JOptionPane;

public class MemoryBoardView extends JFrame implements ActionListener{  
	MemoryGame game;
	JPanel p;

	public MemoryBoardView(MemoryGame game) {  
		super("Memory");
		this.game = game;

		this.p = new JPanel();// creating instance of JPanel

		setSize(500,300);//400 width and 500 height  
		setLayout(null);//using no layout managers  

		this.p.setSize(500, 200);
		this.PlacingTiles();

		add(p);
	} 

	private void PlacingTiles() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {

				// create a new button
				JButton btn = new JButton(" ");
				String btn_command = Integer.toString(i) + " " + Integer.toString(j);
				System.out.println(btn_command);
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
		System.out.println("button clicked");
		JButton b = (JButton) e.getSource();
		String position = b.getActionCommand();
		System.out.println(position);
	}
}