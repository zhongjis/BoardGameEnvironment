package BoardGameEnvironment;

public class BattleShipPiece extends Piece {
	private int state; 

	private static int MISS = 3;
    private static int HIT = 2;
    private static int SHIP = 1;
    private static int WATER = 0;
    
	public BattleShipPiece() {
		super(0);
		this.state = WATER;
	}

	public BattleShipPiece(int id) {
		super(id);
		this.state = WATER;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int s) {
		this.state = s;
	}
 
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return Integer.toString(this.id);
	}
}