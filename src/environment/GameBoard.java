package environment;

import java.util.ArrayList;

public class GameBoard{
	private int width, height;
	ArrayList<ArrayList<Piece>> boardArray = new ArrayList<ArrayList<Piece>>();
	
	GameBoard(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public Piece setMove(int x, int y, Piece piece) {
		// get(y) is to get the second list, set(x, piece) is to set the second value
		// of this list to piece
		return boardArray.get(y).set(x, piece);
		}
	
	public boolean clearBoard() {
		for(ArrayList<Piece> row: boardArray) {
			for(Piece value: row) {
				value = null;
			}
		}
		return true;
	}
}