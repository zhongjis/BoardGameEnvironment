package BoardGameEnvironment.Checkers;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class CheckersPieceView {
	protected int x;
	protected int y;
	private final static int DIMENSION = 100;
	private CheckersPiece piece;

	CheckersPieceView(CheckersPiece piece){
		this.piece = piece;
	}
	
	CheckersPieceView(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g, int x, int y) {
		if(piece.getId() == 1) {
			g.setColor(Color.BLACK);
			g.fillOval(x, y, DIMENSION, DIMENSION);
			g.setColor(Color.WHITE);
			g.drawOval(x, y, DIMENSION, DIMENSION);
		}
		else if(piece.getId() == 2) {
			g.setColor(Color.RED);
			g.fillOval(x, y, DIMENSION, DIMENSION);
			g.setColor(Color.WHITE);
			g.drawOval(x, y, DIMENSION, DIMENSION);
		}
		if(piece.getType().equals("king")) {
			g.setFont(new Font("TimesRoman", Font.BOLD, 14));
			g.drawString("KING", x+35, y+55);
		}
	}
	
	public void drawActive(Graphics g, int x, int y) {
		if(piece.getId() == 1) {
			g.setColor(Color.BLACK);
			g.fillOval(x, y, DIMENSION, DIMENSION);
			g.setColor(Color.YELLOW);
			g.drawOval(x, y, DIMENSION, DIMENSION);
		}
		else if(piece.getId() == 2) {
			g.setColor(Color.RED);
			g.fillOval(x, y, DIMENSION, DIMENSION);
			g.setColor(Color.YELLOW);
			g.drawOval(x, y, DIMENSION, DIMENSION);
		}
		if(piece.getType().equals("king")) {
			g.setFont(new Font("TimesRoman", Font.BOLD, 14));
			g.drawString("KING", x+35, y+55);
		}
	}
	
	public void drawAvailableMove(Graphics g, int x, int y) {
		g.setColor(Color.ORANGE);
		g.fillOval(x+42, y+42, DIMENSION/5, DIMENSION/5);
		g.setColor(Color.RED);
		g.drawOval(x+42, y+42, DIMENSION/5, DIMENSION/5);
	}
}
