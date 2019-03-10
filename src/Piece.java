
public class Piece {
	int player;
	String image;
	
	Piece(){
		player = 0;
		image = Integer.toString(player);
	}
	
	Piece(int p){
		player = p;
		image = Integer.toString(player);
	}
	
	Piece(int p, String img){
		player = p;
		image = img;
	}
}
