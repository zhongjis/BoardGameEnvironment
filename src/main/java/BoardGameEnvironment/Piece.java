/*
 * This file is for pieces will be placed on gameBoard
 */

package BoardGameEnvironment;

public class Piece {
	protected int id;

	public Piece() {
		this.id = 0;
	}
	
	public Piece(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	// @Override
	// public String toString();
}