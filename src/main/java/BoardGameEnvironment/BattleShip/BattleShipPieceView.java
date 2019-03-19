package BoardGameEnvironment;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class BattleShipPieceView {
	protected int x;
	protected int y;
	private final static int DIMENSION = 50;
	private BattleShipPiece piece;
	
	private static int MISS = 3;
    private static int HIT = 2;
    private static int SHIP = 1;
    private static int WATER = 0;

    BattleShipPieceView(BattleShipPiece piece){
		this.piece = piece;
	}
	
    BattleShipPieceView(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g, int x, int y) {
		if(piece.getState() == MISS) {
			g.setColor(Color.BLUE);
			g.fillRect(y, x, DIMENSION, DIMENSION);
			g.setColor(Color.BLACK);
			g.drawRect(y, x, DIMENSION, DIMENSION);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 34)); 
	        g.drawString("X", y + 12, x+ 37); 
		}
		else if(piece.getState() == HIT) {
			g.setColor(Color.RED);
			g.fillRect(y, x, DIMENSION, DIMENSION);
			g.setColor(Color.BLACK);
			g.drawRect(y, x, DIMENSION, DIMENSION);
		}
		else if(piece.getState() == WATER || piece.getState() == SHIP) { //ship is INVSIBILE
			g.setColor(Color.BLUE);
			g.fillRect(y, x, DIMENSION, DIMENSION);
			g.setColor(Color.BLACK);
			g.drawRect(y, x, DIMENSION, DIMENSION);
		}
	}
	
	
	
	
}