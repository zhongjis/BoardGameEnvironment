package BoardGameEnvironment.Checkers;

import BoardGameEnvironment.Piece;

public class CheckersPiece extends Piece {
	private String image;
	private String type = "regular";
	
	public CheckersPiece(){
		super();
		image = Integer.toString(getId());
	}
	
	CheckersPiece(int p){
		super(p);
		image = Integer.toString(getId());
	}
	
	CheckersPiece(int p, String img){
		super(p);
		image = img;
	}
	
	public void convertToKing(){
		this.type = "king";
	}
	
	public String getType() {
		return type;
	}
}
