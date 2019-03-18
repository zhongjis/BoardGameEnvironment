/*
 * This file is board class
 */
package BoardGameEnvironment;

abstract class GameBoard {
	int width;
	int height;
	Piece[][] boardArray;

	/**
	 * init GameBoard with given width & height & game type. about game type please checkout PieceFactory.java
	 * @param  width  [board width]
	 * @param  height [board height]
	 * @return        [null]
	 */
	public GameBoard(int width, int height, String gameType) {
		this.width = width;
		this.height = height;
		boardArray = new Piece[height][width];

		// populate default pieces into the board
		for(int i=0; i< this.boardArray.length; i++){
			for(int j = 0; j <this.boardArray[i].length; j++) {
				boardArray[i][j] = PieceFactory.createPiece(gameType);
			}
		}
	}

	/**
	 * Implement this function to populate your board with
	 * actual game pieces. Call this function right after you
	 * initalize GameBoard.
	 */
	abstract void initializeGameBoard();

	/**
	 * [getBoardArray]
	 * @return [description]
	 */
	public Piece[][] getBoardArray(){
		return this.boardArray;
	}

	/**
	 * get a specifc piece obejct on the baord at the location: x, y
	 * @param  x [x-axis]
	 * @param  y [y-axis]
	 * @return   [piece obejct]
	 */
	public Piece getPiece(int x, int y) {
		if(x < this.boardArray.length && y < this.boardArray[x].length) {
			return this.boardArray[x][y];
		}else {
			return null;
		}
	}

	/**
	 * place a specific piece object on the board object
	 * @param  x     [x-axis]
	 * @param  y     [y-axis]
	 * @param  piece [piece obejct]
	 * @return       [return true if the action is successful, vice-versa]
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
	 * override exisiting print and print out the board, pieces are presented by their names
	 * @return [game board]
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