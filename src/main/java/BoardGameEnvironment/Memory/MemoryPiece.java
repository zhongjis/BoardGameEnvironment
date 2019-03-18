/*
 * Piece obejct for Memory game only
 */

package BoardGameEnvironment.Memory;

import BoardGameEnvironment.Piece;

public class MemoryPiece extends Piece {
	private Boolean matched; // FIXME: this value is never used in MemoryGame 

	public MemoryPiece() {
		super(0);
		this.matched = false;
	}

	public MemoryPiece(int id) {
		super(id);
		this.matched = false;
	}

	public Boolean getMatched() {
		return this.matched;
	}

	public void setMatched(Boolean matched) {
		this.matched = matched;
	}
 
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return Integer.toString(this.id);
	}
}