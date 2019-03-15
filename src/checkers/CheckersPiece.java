package checkers;
import BoardGameEnvironment.Piece;

public class CheckersPiece extends Piece{
	int player;
	String image;
	String type = "regular";
	
	public CheckersPiece(){
		super.setId(0);
		player = 0;
		image = Integer.toString(player);
	}
	
	CheckersPiece(int p){
		super.setId(p);
		player = p;
		image = Integer.toString(player);
	}
	
	CheckersPiece(int p, String img){
		player = p;
		image = img;
	}
	
	public void convertToKing(){
		this.type = "king";
	}
	
	public String getType() {
		return type;
	}
}
