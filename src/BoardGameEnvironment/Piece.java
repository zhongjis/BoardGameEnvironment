/*
 * This file is for pieces will be placed on gameBoard
 * you suppose to create your own piece class inherited from this
 */

package BoardGameEnvironment;

public class Piece {
	protected int id;
	protected User user;

	public Piece() {
		this.id = 0;
	}

	public Piece(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	// @Override
	// public String toString();
}