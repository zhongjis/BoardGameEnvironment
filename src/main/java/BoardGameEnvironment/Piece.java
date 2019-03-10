/*
 * This file is for pieces will be placed on gameBoard
 */
package BoardGameEnvironment;

public abstract class Piece {
	protected int id;

	public Piece(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public abstract String toString();
}