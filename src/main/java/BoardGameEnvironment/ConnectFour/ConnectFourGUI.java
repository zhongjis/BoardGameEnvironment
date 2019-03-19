package BoardGameEnvironment.ConnectFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import BoardGameEnvironment.User;

public class ConnectFourGUI extends JPanel implements ActionListener{
	Timer tm;
	public int width;
	public int height;
	int boardWidth;
	int boardHeight;
	int row;
	int col;
	
	final int MAX = 99999;
	final int horPadding = 100;
	final int verPadding = 150;
	final int pieceHorPadding = 30;
	final int pieceVerPadding = 30;
	final int pieceSize = 60;
	final int fallingVelocity = 300;
	final int optionY = verPadding-pieceSize-pieceVerPadding;
	int mouseOnCol;
	int clickedOnCol;
	int curY;
	JLabel statusBar,gameoverLabel;
	JPanel statusPanel;
	JButton restartButton;
	
	
	private ConnectFourGame game;
	
	private class Handler implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			mouseOnCol = pixToCol(x,y);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if(curY == MAX && !game.isFull() &&!game.isWin)
			{
				clickedOnCol = pixToCol(x,y);
				if(game.validX(clickedOnCol))
				{
					curY = optionY;
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public int pixToCol(int x, int y)
	{
		if(y>=verPadding && y<=verPadding+boardHeight && x>=horPadding && x<=horPadding+boardWidth)
		{
			int firstPieceRange = horPadding+(int)(pieceHorPadding*1.5) + pieceSize; 
			if(x<firstPieceRange)
			{
				return 0;
			}
			else
			{
				int c = (x-firstPieceRange)/(pieceSize+pieceHorPadding)+1;
				if(c==col || c==col-1)
				{
					return col-1;
				}
				return c;
			}
		}
		
		return -1;
	}
	
	public ConnectFourGUI(int row, int col, ConnectFourGame g)
	{
		this.game = g;
		this.col = col;
		this.row = row;
		this.mouseOnCol = -1;
		this.clickedOnCol = -1;
		this.curY = MAX ;
		
		this.boardWidth = this.col*pieceSize+pieceHorPadding*(col+1) ;
		this.boardHeight = this.row*pieceSize+pieceVerPadding*(row+1);
		this.width = this.boardWidth + horPadding * 2;
		this.height = this.boardHeight + verPadding *2;
		this.setSize(width,height);
		this.setBackground(Color.BLACK);
		

		this.setLayout(new BorderLayout());
		
		tm = new Timer(100,this);
		
		
		statusBar = new JLabel("Status");
		statusBar.setFont(new Font("Arial",Font.BOLD,50));
		statusPanel = new JPanel();
		statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		statusPanel.add(statusBar);
		statusPanel.setBackground(this.getColor(this.game.getTurn()));
		
		gameoverLabel = new JLabel("GAME OVER");
		gameoverLabel.setFont(new Font("Arial",Font.BOLD,120));
		gameoverLabel.setHorizontalAlignment(JLabel.CENTER);
		
		restartButton = new JButton("Play Again!");
		restartButton.setFont(new Font("Arial", Font.PLAIN,40));
		restartButton.setBounds(500, 500, 100, 100);
		restartButton.setBackground(Color.pink);
		restartButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					game = new ConnectFourGame(game.getPlayers());
					setBackground(Color.BLACK);
					remove(gameoverLabel);
					remove(restartButton);
				}
			}
		);
		
		

		this.add(statusPanel,BorderLayout.SOUTH);
		
		Handler handler = new Handler();
		this.addMouseMotionListener(handler);
		this.addMouseListener(handler);
		
		
		
			
		tm.start();
	
		//this.setVisible(true);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
		
	}
	
	public void drawBoard(Graphics g)
	{
		g.setColor(Color.CYAN);
		g.fillRect(horPadding, verPadding, boardWidth, boardHeight);
	}
	
	public void drawGravity(Graphics g)
	{
		if(curY < this.calcPixY(this.game.yvalues[clickedOnCol]))
		{
			this.setColor(g, this.game.getTurn());
			g.fillOval(calcPixX(this.clickedOnCol), 
					curY, pieceSize, pieceSize);
			curY+=this.fallingVelocity;
		}
		
		else {
			curY = MAX;
		}
	}
	
	public void makeMove(Graphics g)
	{
		if(this.clickedOnCol!=-1 && this.game.validX(clickedOnCol))
		{
			this.drawGravity(g);
			if(curY==MAX) {
				this.setColor(g, this.game.getTurn());
				g.fillOval(calcPixX(this.clickedOnCol), 
						this.calcPixY(this.game.yvalues[this.clickedOnCol]), pieceSize, pieceSize);
				
				this.game.colNum = this.clickedOnCol;
				this.game.run();
				this.clickedOnCol = -1;
			}
		}
	}
	
	public int calcPixX(int c)
	{
		return horPadding + c*pieceSize + (c+1)*pieceHorPadding;
	}
	
	public int calcPixY(int r)
	{
		return verPadding + r*pieceSize + (r+1)*pieceVerPadding;
	}
	
	
	public void setColor(Graphics g, int color)
	{
		if(color == ConnectFourGame.red)
		{
			g.setColor(Color.RED);
			
		}
		else if(color == ConnectFourGame.yellow) {
			g.setColor(Color.YELLOW);
		
		}
		else
		{
			g.setColor(Color.WHITE);
		
		}
	}
	
	public Color getColor(int color)
	{
		if(color == ConnectFourGame.red)
		{
			return Color.RED;
		}
		else if(color == ConnectFourGame.yellow) {
			return Color.YELLOW;
		}
		else
		{
			return Color.WHITE;
		}
	}
	
	
	public void drawOptions(Graphics g)
	{
		if(!this.game.isWin && !this.game.isFull())
		{
			this.setColor(g, this.game.getTurn());
			if(this.mouseOnCol!=-1)
				g.fillOval(calcPixX(this.mouseOnCol), 
					optionY, pieceSize,pieceSize);
		}
	}
	
	public void drawPiece(Graphics g, int x, int y)
	{
		
		this.setColor(g, this.game.getBoard().getPiece(y, x).getId());
		g.fillOval(this.calcPixX(x), this.calcPixY(y), pieceSize, pieceSize); 	
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.drawBoard(g);
		for(int r = 0; r < this.row; r++ )
		{
			for(int c = 0; c < this.col; c++)
			{
				this.drawPiece(g, c, r);
			}
		}
		this.drawOptions(g);
		this.makeMove(g);
		this.statusBar.setText(this.game.getPlayer().getName() + "'s turn");
		if(this.game.isFull())
		{
			this.statusBar.setText("Tied Game");
			
		}
		
		if(this.game.isWin)
		{
			String str = String.format("%s(%s) won!", this.game.getPlayer().getName(),this.game.getColor());
			this.statusBar.setText(str);
			this.setBackground(this.getColor(this.game.getTurn()));
			this.add(gameoverLabel,BorderLayout.CENTER);
			this.add(restartButton,BorderLayout.NORTH);
			restartButton.setBounds(this.width/2-150,optionY,300,verPadding/2);
			this.game.end();
			this.game.isWin = false;
		}
		
		statusPanel.setBackground(this.getColor(this.game.getTurn()));
			
	}
	
}
