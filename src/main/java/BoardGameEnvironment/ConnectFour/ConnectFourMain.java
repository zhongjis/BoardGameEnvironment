package BoardGameEnvironment.ConnectFour;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import BoardGameEnvironment.User;

public class ConnectFourMain extends JFrame {
	
	public ConnectFourMain(User[]players) {
		ConnectFourGUI GUI= new ConnectFourGUI(8,8, new ConnectFourGame(players)); 
		this.setSize(GUI.width,GUI.height);
		
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		this.add(GUI,BorderLayout.CENTER);
		this.setTitle("Connect Four");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
	
	public void run()
	{
		this.setVisible(true);
	}
	public static void main(String[]args)
	{
		User[]players = {new User("playerA"),new User("playerB")};
		ConnectFourMain main = new ConnectFourMain(players);
		main.run();
	}
}
