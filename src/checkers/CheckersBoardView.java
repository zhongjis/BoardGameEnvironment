package checkers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
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
	private CheckersLocation reCapture = null;
	
	public CheckersBoardView(CheckersGame gameState) {
		this.gameState = gameState;
		for(int row = 0; row < checkersPieces.length; row++) {
			for(int col = 0; col < checkersPieces[row].length; col++) {
				checkersPieces[row][col] = new CheckersPieceView(gameState.board.getPiece(row, col));
			}
		}
		dimSize = new Dimension(BOARDDIM, BOARDDIM);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if(!gameState.end) {
					int x = me.getX();
					int y = me.getY();
					int boardX = (int)Math.floor(x/100.0);
					int boardY = (int)Math.floor(y/100.0);
					CheckersLocation click = new CheckersLocation(boardY, boardX);
					if(reCapture == null) {
						ArrayList<CheckersLocation> availablePieces = gameState.startOfTurn();
						if(gameState.checkValidSelection(click) && checkInList(click, availablePieces)){
							draggingPiece = new CheckersPieceView(boardX*SQUAREDIM, boardY*SQUAREDIM);
							oldX = boardX;
							oldY = boardY;
							dragging = true;
						}
					}else {
						if(gameState.checkValidSelection(click) && click.getX() == reCapture.getX() && click.getY() == reCapture.getY()) {
							draggingPiece = new CheckersPieceView(boardX*SQUAREDIM, boardY*SQUAREDIM);
							oldX = boardX;
							oldY = boardY;
							dragging = true;
						}
					}
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
				if(reCapture == null) {
					ArrayList<CheckersLocation> checkMoves = gameState.checkAvailableMoves(oldClick, gameState.board.getPiece(oldClick.getX(), oldClick.getY()).getType());
					if(checkInList(drop, checkMoves)){
						CheckersLocation middle = gameState.getMiddlePiece(oldClick, drop);
						if(middle != null) {
							if(gameState.capture(middle, drop) != null)
								gameState.end = true;
							ArrayList<CheckersLocation> availableReCaptures = gameState.checkReCapturable(drop);
							if(availableReCaptures.size() != 0)
								reCapture = drop;
						}
						gameState.movePiece(oldClick, drop);
						if(!gameState.end) {
							if(reCapture == null)
								gameState.changeTurn();
						}
						gameState.turnNumber++;
					}
				}else {
					if(!gameState.end){
						ArrayList<CheckersLocation> availableReCaptures = gameState.checkReCapturable(reCapture);
						if(checkInList(drop, availableReCaptures)){
							CheckersLocation middle = gameState.getMiddlePiece(reCapture, drop);
							if(gameState.capture(middle, drop) != null) {
								gameState.movePiece(reCapture, drop);
								gameState.end = true;
							}
							else {
								gameState.movePiece(reCapture, drop);
								reCapture = drop;
								gameState.turnNumber++;
								availableReCaptures = gameState.checkReCapturable(drop);
								if(availableReCaptures.size() == 0) {
									reCapture = null;
									gameState.changeTurn();
								}
							}
						}
					}
				}
				updateGameViewBoard();
				repaint();
				draggingPiece = null;
				if(gameState.end) {
					repaint();
					return;
				}
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				if(dragging) {
					int x = me.getX()-(SQUAREDIM/2);
					int y = me.getY()-(SQUAREDIM/2);
					checkersPieces[oldY][oldX].x = x;
					checkersPieces[oldY][oldX].y = y;
					repaint();
				}
			}
		});
	}
	
	public void paintGameOver(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 72));
		g.setColor(new Color(37, 53, 255, 255));
		g.drawString("GAME OVER", 180, 425);
		g.setFont(new Font("TimesRoman", Font.BOLD, 48));
		g.setColor(new Color(37, 197, 70, 255));
		if(gameState.playerTurn == 1)
			g.drawString(gameState.usersList.get(0).getName(), 300, 485);
		else
			g.drawString(gameState.usersList.get(1).getName(), 300, 485);
		g.drawString("WINS", 335, 545);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return dimSize;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		paintCheckersBoard(g);
		paintCheckersPieces(g);
		if(gameState.end)
			paintGameOver(g);
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
			if(!gameState.end) {
				g.setColor(((row % 2) == 0) ? Color.WHITE : Color.BLACK);
				for(int col=0;col<8;col++) {
					g.fillRect(col * SQUAREDIM, row * SQUAREDIM, SQUAREDIM, SQUAREDIM);
					g.setColor((g.getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE);
				}
			}else {
				Color newWhite = new Color(255, 255, 255, 175);
				Color newBlack = new Color(0, 0, 0, 175);
				g.setColor(((row % 2) == 0) ? newWhite : newBlack);
				for(int col=0;col<8;col++) {
					g.fillRect(col * SQUAREDIM, row * SQUAREDIM, SQUAREDIM, SQUAREDIM);
					g.setColor((g.getColor() == newWhite) ? newBlack : newWhite);
				}
			}
		}
		g.setFont(new Font("TimesRoman", Font.BOLD, 18));
		g.setColor(new Color(1, 135, 7, 255));
		g.drawString("TURN:", 4, 17);
		g.drawString(Integer.toString(gameState.turnNumber), 64, 17);
		if(gameState.playerTurn == 1) {
			g.setColor(Color.BLACK);
			g.drawString(gameState.usersList.get(0).getName(), 4, 37);
		}
		else {
			g.setColor(Color.RED);
			g.drawString(gameState.usersList.get(1).getName(), 4, 37);
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