package checkers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JComponent;

public class CheckersBoardView extends JComponent{
	private CheckersGame gameState;
	private static final int SQUAREDIM = 100;
	private static final int BOARDDIM = SQUAREDIM * 8;
	private Dimension dimSize;
	private boolean dragging = false;
	private CheckersPieceView[][] checkersPieces = new CheckersPieceView[8][8];
	private CheckersPieceView draggingPiece;
	private int oldX, oldY;
	
	public CheckersBoardView(CheckersGame gameState) {
		for(int row = 0; row < checkersPieces.length; row++) {
			for(int col = 0; col < checkersPieces[row].length; col++) {
				checkersPieces[row][col] = new CheckersPieceView(gameState.board.getPiece(row, col));
			}
		}
		this.gameState = gameState;
		dimSize = new Dimension(BOARDDIM, BOARDDIM);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				int x = me.getX();
				int y = me.getY();
				int boardX = (int)Math.floor(x/100.0);
				int boardY = (int)Math.floor(y/100.0);
				ArrayList<CheckersLocation> availablePieces = gameState.startOfTurn();
				CheckersLocation click = new CheckersLocation(boardY, boardX);
				if(gameState.checkValidSelection(click) && checkInList(click, availablePieces)){
					draggingPiece = new CheckersPieceView(new CheckersPiece());
					draggingPiece.x = boardX*SQUAREDIM;
					draggingPiece.y = boardY*SQUAREDIM;
					oldX = boardX;
					oldY = boardY;
					dragging = true;
				}
			}
			
			public void mouseReleased(MouseEvent me) {
				if(dragging)
					dragging = false;
				else
					return;
				CheckersLocation oldClick = new CheckersLocation(oldY, oldX);
				int x = me.getX();
				int y = me.getY();
				int boardX = (int)Math.floor(x/100.0);
				int boardY = (int)Math.floor(y/100.0);
				CheckersLocation drop = new CheckersLocation(boardY, boardX);
				ArrayList<CheckersLocation> checkMoves = gameState.checkAvailableMoves(oldClick, gameState.board.getPiece(oldClick.getX(), oldClick.getY()).getType());
				if(checkInList(drop, checkMoves)){
					CheckersLocation middle = gameState.getMiddlePiece(oldClick, drop);
					CheckersLocation tempCoord = null;
					if(middle != null) {
						tempCoord = drop;
						if(gameState.capture(middle, drop) != null)
							gameState.end = true;
						gameState.movePiece(oldClick, drop);
						
					}else
						gameState.movePiece(oldClick, drop);
					if(gameState.end) {
						gameState.end(); 
						return;	// change this if block to actually end the game
					}
					gameState.changeTurn();
					gameState.turnNumber++;
				}
				updateGameViewBoard();
				repaint();
				draggingPiece = null;
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				if(dragging) {
					int x = me.getX();
					int y = me.getY();
					checkersPieces[oldY][oldX].x = x;
					checkersPieces[oldY][oldX].y = y;
					repaint();
				}
			}
		});
	}
	
	@Override
	public Dimension getPreferredSize() {
		return dimSize;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		paintCheckersBoard(g);
		paintCheckersPieces(g);
	}
	
	private void paintCheckersPieces(Graphics g) {		
		for(int row = 0; row < checkersPieces.length; row++) {
			for(int col = 0; col < checkersPieces[row].length; col++) {
				checkersPieces[row][col].draw(g, col*SQUAREDIM, row*SQUAREDIM);
			}
		}
		if(draggingPiece != null) {
			g.setColor(Color.BLACK);
			g.fillRect(draggingPiece.x, draggingPiece.y, SQUAREDIM, SQUAREDIM);
			checkersPieces[oldY][oldX].draw(g, checkersPieces[oldY][oldX].x, checkersPieces[oldY][oldX].y);
		}
	}
	
	private void paintCheckersBoard(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(int row=0;row<8;row++) {
			g.setColor(((row % 2) == 0) ? Color.WHITE : Color.BLACK);
			for(int col=0;col<8;col++) {
				g.fillRect(col * SQUAREDIM, row * SQUAREDIM, SQUAREDIM, SQUAREDIM);
				g.setColor((g.getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE);
			}
		}
	}
	
	private boolean checkInList(CheckersLocation coord, ArrayList<CheckersLocation> list) {
		for(CheckersLocation element : list) {
			if(element.getX() == coord.getX() && element.getY() == coord.getY())
				return true;
		}
		return false;
	}
	
	private void updateGameViewBoard() {
		for(int row = 0; row < checkersPieces.length; row++) {
			for(int col = 0; col < checkersPieces[row].length; col++) {
				checkersPieces[row][col] = new CheckersPieceView(gameState.board.getPiece(row, col));
			}
		}
	}
}