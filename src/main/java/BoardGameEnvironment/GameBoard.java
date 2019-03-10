/*
 * This file is board class
 */
package BoardGameEnvironment;
import java.util.ArrayList;

public class GameBoard{
	int width;
	int height;
	Piece[][] boardArray;

	/**
	 * init GameBoard with given width & height
	 * @param  width  [description]
	 * @param  height [description]
	 * @return        [description]
	 */
	public GameBoard(int width, int height) {
		this.width = width;
		this.height = height;
		boardArray = new Piece[width][height];

		// populate default pieces into the board
		for(int i=0; i< this.boardArray.length; i++){
			for(int j = 0; j <this.boardArray[i].length; j++) {
				boardArray[i][j] = new Piece();
			}
		}
	}

	/**
	 * [getBoardArray description]
	 * @return [description]
	 */
	public Piece[][] getBoardArray(){
		return this.boardArray;
	}

	/**
	 * [getPiece description]
	 * @param  x [description]
	 * @param  y [description]
	 * @return   [description]
	 */
	public Piece getPiece(int x, int y) {
		if(x < this.boardArray.length && y < this.boardArray[x].length) {
			return this.boardArray[x][y];
		}else {
			return null;
		}
	}

	/**
	 * [setPiece description]
	 * @param  x     [description]
	 * @param  y     [description]
	 * @param  piece [description]
	 * @return       [description]
	 */
	public boolean setPiece(int x, int y, Piece piece) {
		if(x < this.boardArray.length && y < this.boardArray[x].length) {
			this.boardArray[x][y] = piece;
			return true;	
		}else {
			return false;
		}
	}

	/**
	 * override exisiting print and print out the baord
	 * @return [description]
	 */
	@Override
	public String toString(){
		String output = "";

		for(int i=0; i< this.boardArray.length; i++){
			for(int j = 0; j <this.boardArray[i].length; j++) {
				output += boardArray[i][j].getId() + " ";
			}
			output += "\n";
		}

		return output;
	}
}