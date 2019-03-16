package checkers;
import java.awt.Graphics;
import java.awt.Color;

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
		if(piece.getType().equals("king"))
			g.drawString("KING", x+50, y+50);
	}
	
}
