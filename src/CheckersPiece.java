
public class CheckersPiece {
	int player;
	String image;
	String type = "regular";
	
	CheckersPiece(){
		player = 0;
		image = Integer.toString(player);
	}
	
	CheckersPiece(int p){
		player = p;
		image = Integer.toString(player);
	}
	
	CheckersPiece(int p, String img){
		player = p;
		image = img;
	}
}
