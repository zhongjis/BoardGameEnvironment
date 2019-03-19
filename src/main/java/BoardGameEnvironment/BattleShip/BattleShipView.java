package BoardGameEnvironment.BattleShip;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JComponent;





public class BattleShipView extends JComponent{
	private BattleShip gameState;
	private static final int SQUAREDIM = 50;
	private static final int BOARDDIM = SQUAREDIM * 10;
	private Dimension dimSize;
	private boolean dragging = false;
	private BattleShipPieceView[][] BattlePieces = new BattleShipPieceView[10][10];
	
	public BattleShipView(BattleShip gameState) {
		this.gameState = gameState;
		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {
				BattlePieces[row][col] = new BattleShipPieceView((BattleShipPiece) gameState.getPiece(row, col));
			}
		}
		dimSize = new Dimension(BOARDDIM, BOARDDIM);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouse) {
				if(!gameState.end()) {
					int x = mouse.getX();
					int y = mouse.getY();
					int boardX = (int)Math.floor(x/50.0);
					int boardY = (int)Math.floor(y/50.0);
					System.out.println(boardX + "   " + boardY);
					
					//GET X and Y thenm check if box has been checked yet otherwise do nothing
					if(gameState.playMove(boardX, boardY, gameState.getPlayer())) {
						gameState.checkIfWin();
					}
					
				}
			}
			
			public void mouseReleased(MouseEvent me) {
				updateGameViewBoard();
				repaint();
				if(gameState.end()) {
					repaint();
					return;
				}
			}
		});
	}
		
	
	
	public void paintGameOver(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 36));
		g.setColor(Color.WHITE);
		g.drawString("GAME OVER", 90, 212);
		g.setFont(new Font("TimesRoman", Font.BOLD, 24));
		g.setColor(Color.WHITE);
		if(gameState.turn % 2 == 0) //p1
			g.drawString(gameState.players[0].getName(), 165
					, 250);
		else //p2
			g.drawString(gameState.players[1].getName(), 165, 250);
		g.drawString("WINS", 165, 300);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return dimSize;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(3));
		paintBattleShipPieces(g);
		paintBattleShipBoard(g);
		if(gameState.end())
			paintGameOver(g);
	}
		
	
	
	
	private void paintBattleShipBoard(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 18));
		g.setColor(new Color(1, 135, 7, 255));
		g.drawString("TURN:", 4, 17);
		g.drawString(Integer.toString(gameState.turn), 64, 17);
		if(gameState.turn % 2 == 0) {
			g.setColor(Color.GREEN);
			g.drawString(gameState.players[0].getName(), 4, 37);
			g.drawString(((Integer) gameState.playerScore[0]).toString(), 4, 57);
		}
		else {
			g.setColor(Color.RED);
			g.drawString(gameState.players[1].getName(), 4, 37);
			g.drawString(((Integer) gameState.playerScore[1]).toString(), 4, 57);
		}
	}
	
	private void paintBattleShipPieces(Graphics g) {		
		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {
				BattlePieces[row][col].draw(g, col*SQUAREDIM, row*SQUAREDIM);
			}
		}
		
}
	
	
	private void updateGameViewBoard() {
		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {
				BattlePieces[row][col] = new BattleShipPieceView((BattleShipPiece) gameState.getPiece(row, col));
			}
			
			
		}
	}
}
